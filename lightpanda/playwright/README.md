# ismywebsiteup checker (Playwright + Chrome/Lightpanda)

Checks a list of URLs, records status/load time, and writes a summary CSV.

## Requirements

- Node.js 18+
- Google Chrome installed (if using `chrome`)
- Lightpanda available via npm dependency (if using `lightpanda`)

## Setup

```bash
cd playwright-chrome
npm install
```

## Input file format

Provide a text file with one URL per line:

```text
https://example.com
example.org
https://httpstat.us/404
```

Blank lines are ignored. If a URL does not include a scheme, `https://` is prepended.

## Run

```bash
node check-websites.js <path-to-url-file> [chrome|lightpanda]
```

Example:

```bash
node check-websites.js ..\\urls.txt chrome
node check-websites.js ..\\urls.txt lightpanda
```

If browser is omitted, the script defaults to `chrome`.

When using `lightpanda`, the script starts a local Lightpanda CDP server and
connects to it with Playwright (`chromium.connectOverCDP`). You can override
host/port with:

- `LIGHTPANDA_HOST` (default: `127.0.0.1`)
- `LIGHTPANDA_PORT` (default: `9222`)

## Output

Artifacts are written to:

```text
playwright-chrome/reports/YYYY-MM-DDTHH-mm-ss_<browser>/
```

Inside that folder:

- `summary.csv` with columns:
  - `status,url,time_to_load,final_url,ready_state,request_count,response_count,main_document_response_count,main_document_statuses`

The script also logs:

- Start timestamp (ISO 8601)
- End timestamp (ISO 8601)
- Total runtime in milliseconds

Status values may be:

- HTTP status code (for example `200`, `404`)
- `NO_MAIN_RESPONSE` when navigation succeeds but no main response object is returned
- `NAVIGATION_ERROR` when `page.goto` throws; the console log includes the error message


NOTES:

- as of 2026/03/31 lightpanda did not work on windows, and it was having issues with some urls I passed in - still a work in progress

