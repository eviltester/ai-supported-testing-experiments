const fs = require("fs");
const path = require("path");
const { performance } = require("perf_hooks");
const { chromium } = require("playwright");

const NAVIGATION_TIMEOUT_MS = 30000;
const SUPPORTED_BROWSERS = new Set(["chrome", "lightpanda"]);

function printUsage() {
  console.error("Usage: node check-websites.js <path-to-url-file> [chrome|lightpanda]");
}

function toSortableTimestamp(date = new Date()) {
  const iso = date.toISOString();
  return iso.slice(0, 19).replace(/:/g, "-");
}

function toIsoTimestamp(date = new Date()) {
  return date.toISOString();
}

function normalizeUrl(input) {
  if (/^[a-zA-Z][a-zA-Z\d+.-]*:\/\//.test(input)) {
    return input;
  }
  return `https://${input}`;
}

function csvEscape(value) {
  const asString = String(value ?? "");
  if (/[",\n\r]/.test(asString)) {
    return `"${asString.replace(/"/g, '""')}"`;
  }
  return asString;
}

function normalizeBrowserName(input) {
  const browserName = (input ?? "chrome").trim().toLowerCase();
  return browserName.length > 0 ? browserName : "chrome";
}

async function createBrowserRuntime(browserName) {
  if (browserName === "chrome") {
    return {
      launchBrowser: () =>
        chromium.launch({
          channel: "chrome",
          headless: true,
        }),
      cleanup: async () => {},
    };
  }

  let lightpanda;
  try {
    ({ lightpanda } = require("@lightpanda/browser"));
  } catch (error) {
    throw new Error(
      "Lightpanda runtime is not available. Install @lightpanda/browser and ensure your platform is supported."
    );
  }
  const host = process.env.LIGHTPANDA_HOST || "127.0.0.1";
  const port = Number(process.env.LIGHTPANDA_PORT || 9222);
  const proc = await lightpanda.serve({ host, port });
  const endpointURL = `ws://${host}:${port}`;

  return {
    launchBrowser: () =>
      chromium.connectOverCDP({
        endpointURL,
      }),
    cleanup: async () => {
      if (proc && !proc.killed) {
        if (proc.stdout && typeof proc.stdout.destroy === "function") {
          proc.stdout.destroy();
        }
        if (proc.stderr && typeof proc.stderr.destroy === "function") {
          proc.stderr.destroy();
        }
        proc.kill();
      }
    },
  };
}

async function checkUrl(url, launchBrowser) {
  const browser = await launchBrowser();

  const context = await browser.newContext();
  const page = await context.newPage();
  let requestCount = 0;
  let responseCount = 0;
  let mainDocumentResponseCount = 0;
  const mainDocumentStatuses = [];

  page.on("request", () => {
    requestCount += 1;
  });

  page.on("response", (response) => {
    responseCount += 1;
    const req = response.request();
    if (req && req.isNavigationRequest() && req.resourceType() === "document") {
      mainDocumentResponseCount += 1;
      mainDocumentStatuses.push(response.status());
    }
  });

  let status = "NAVIGATION_ERROR";
  let errorMessage = "";
  const start = performance.now();

  try {
    const response = await page.goto(url, {
      waitUntil: "load",
      timeout: NAVIGATION_TIMEOUT_MS,
    });

    if (response && typeof response.status === "function") {
      status = response.status();
    } else {
      status = "NO_MAIN_RESPONSE";
    }
  } catch (error) {
    status = "NAVIGATION_ERROR";
    errorMessage = error && error.message ? error.message : String(error);
  }

  const timeToLoad = Math.round(performance.now() - start);
  const finalUrl = page.url();
  const readyState = await page
    .evaluate(() => document.readyState)
    .catch(() => "UNAVAILABLE");

  await context.close();
  await browser.close();

  return {
    status,
    url,
    timeToLoad,
    errorMessage,
    finalUrl,
    readyState,
    requestCount,
    responseCount,
    mainDocumentResponseCount,
    mainDocumentStatuses: mainDocumentStatuses.join("|"),
  };
}

async function main() {
  const scriptStartPerf = performance.now();
  const scriptStartDate = new Date();
  const scriptStartTs = toIsoTimestamp(scriptStartDate);
  const inputFilePath = process.argv[2];
  const browserName = normalizeBrowserName(process.argv[3]);

  if (!inputFilePath) {
    printUsage();
    process.exit(1);
  }

  if (!SUPPORTED_BROWSERS.has(browserName)) {
    console.error(`Unsupported browser: ${browserName}`);
    console.error("Supported browsers: chrome, lightpanda");
    printUsage();
    process.exit(1);
  }

  const resolvedInputPath = path.resolve(inputFilePath);

  if (!fs.existsSync(resolvedInputPath)) {
    console.error(`Input file does not exist: ${resolvedInputPath}`);
    printUsage();
    process.exit(1);
  }

  const rawInput = fs.readFileSync(resolvedInputPath, "utf8");
  const urls = rawInput
    .split(/\r?\n/)
    .map((line) => line.trim())
    .filter((line) => line.length > 0)
    .map(normalizeUrl);

  console.log(`Start timestamp: ${scriptStartTs}`);

  const outputDir = path.join(
    __dirname,
    "reports",
    `${toSortableTimestamp()}_${browserName}`
  );
  fs.mkdirSync(outputDir, { recursive: true });

  const results = [];
  const runtime = await createBrowserRuntime(browserName);

  try {
    for (const url of urls) {
      const result = await checkUrl(url, runtime.launchBrowser);
      results.push(result);
      const errorSuffix = result.errorMessage ? ` | ${result.errorMessage}` : "";
      console.log(`[${browserName}] Checked ${url} -> ${result.status} (${result.timeToLoad}ms)${errorSuffix}`);
    }
  } finally {
    await runtime.cleanup();
  }

  const csvLines = [
    "status,url,time_to_load,final_url,ready_state,request_count,response_count,main_document_response_count,main_document_statuses",
  ];
  for (const result of results) {
    csvLines.push(
      `${csvEscape(result.status)},${csvEscape(result.url)},${csvEscape(
        result.timeToLoad
      )},${csvEscape(result.finalUrl)},${csvEscape(result.readyState)},${csvEscape(
        result.requestCount
      )},${csvEscape(result.responseCount)},${csvEscape(
        result.mainDocumentResponseCount
      )},${csvEscape(result.mainDocumentStatuses)}`
    );
  }

  const csvPath = path.join(outputDir, "summary.csv");
  fs.writeFileSync(csvPath, `${csvLines.join("\n")}\n`, "utf8");

  console.log(`Processed ${results.length} URL(s).`);
  console.log(`Report directory: ${outputDir}`);
  console.log(`Summary CSV: ${csvPath}`);
  const scriptEndDate = new Date();
  const scriptEndTs = toIsoTimestamp(scriptEndDate);
  console.log(`End timestamp: ${scriptEndTs}`);
  console.log(`Total runtime: ${Math.round(performance.now() - scriptStartPerf)}ms`);
}

main().catch((error) => {
  console.error("Unexpected failure:", error);
  process.exit(1);
});
