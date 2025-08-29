# 20250829 - Using OSS Models via Ollama

I only have a 16GB Graphics card so I tend to stick to smaller models.

In this experiment I will attempt to replicate the start of the playwright with ChatGPT experiment using offline OSS models through Ollama.

> create a Java maven project using Junit 5 which has a single test that uses playwright to open https://testpages.eviltester.com in a Chrome browser

One issue with models is that they tend to be 'out of date', this is more of an issue with libraries than programming languages so I expect the OSS models to struggle.

I tried the following small models:

- codellama - generated wrong dependencies for `pom.xml` and test did not work without fixing
- codestral - valid dependencies, but test worked fully (first generation), 2nd wasn't good
- qwen2.5:coder - valid dependencies, test did not assert anything
- deepseek-coder v2 - worked
- cogito - invalid dependencies and invalid test code
- starcoder2:7b - did not generate any code
- deepcoder - wrong dependencies and wrong code in test


At times I wondered if ollama was introducing noise into the testing. When I ran models a second time I got different results. Even when creating new chats, I'm wondering if the context was shared between chats. Or perhaps the models are just not consistent.

`deepseek-coder-v2` was the outright winner and I'll experiment with that further.

## codellama:7b

I've had good enough results using codellama:7b. I've primarily been using it for Python and general language information.

It has been very helpful for Python work.

> create a Java maven project using Junit 5 which has a single test that uses playwright to open https://testpages.eviltester.com in a Chrome browser


The suggested dependencies were for `io.playwright`, I couldn't find a maven dependency for io.playwright - https://central.sonatype.com/namespace/io.playwright == 404

```
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.7.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.9.2</version>
    </dependency>
</dependencies>

```

The basic `pom.xml` file seemed otherwise OK.

The test that was generated would not work without amendment.

```
import io.playwright.Browser;
import io.playwright.Page;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MyFirstPlaywrightTest {
    private static Browser browser;
    private static Page page;

    @BeforeAll
    public static void beforeAll() throws Exception {
        browser = playwright.chromium().launch();
        page = browser.newPage();
    }

    @AfterAll
    public static void afterAll() {
        if (browser != null) {
            browser.close();
        }
    }

    @Test
    void testPageTitle() throws Exception {
        page.navigate("https://testpages.eviltester.com");
        assertEquals("Welcome to the Test Pages", page.title());
    }
}
```

In `beforeAll` there was no `playwright` instantiated so I needed to add this `Playwright playwright = Playwright.create();`

Additionally the `assertEquals` had not been statically imported.

After amendment the test did work.

The codellama model seems to have had some training on Playwright but wasn't good at starting the project.

## codellama:13b

The larger `codellama:13b` generated exactly the same output as `7b`

## Codestral

https://ollama.com/library/codestral

I tried the larger `codestral` model.

This also generated the wrong dependency code:

```
<dependencies>
    ...
    <dependency>
        <groupId>io.playwright</groupId>
        <artifactId>playwright-java</artifactId>
        <version>1.24.0</version>
    </dependency>
    ...
</dependencies>
```

The test that was generated was better and had imported playwright from `com.microsoft.playwright` despite not getting the dependency correct.

```
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaywrightTest {
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    public void tearDown() {
        context.close();
        browser.close();
    }

    @Test
    public void testWebsiteTitle() {
        page.navigate("https://testpages.eviltester.com");
        String title = page.title();
        assertEquals("EvilTester.com", title);
    }
}
```

I tried codestral a 2nd time and it generated a test where the code was wrapped in a `try` so would never fail.

```
    @Test
    public void openWebsite() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("https://testpages.eviltester.com");
            assertThat(page).hasTitle("EvilTester's Test Pages");
        }
    }
```

## qwen2.5:coder7b

https://ollama.com/library/qwen2.5-coder

Brought in a com.microsoft.playwright

```
        <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright</artifactId>
            <version>1.22.0</version>
            <scope>test</scope>
        </dependency>
```

The test did assert anything and the name of the class is questionable.

```

public class TestPage {
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeEach
    public void setUp() {
        browser = Playwright.create().chromium().launch();
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void openTestPage() {
        page.navigate("https://testpages.eviltester.com");
        System.out.println("Opened https://testpages.eviltester.com");
    }

    @AfterEach
    public void tearDown() {
        page.close();
        context.close();
        browser.close();
    }
}
```

## deepseek-coder v2

https://ollama.com/library/deepseek-coder-v2

deepseek-coder-v2:16b

The first OSS model which generated the correct `pom.xml` file

using `com.microsoft.playwright`

```
    <dependency>
      <groupId>com.microsoft.playwright</groupId>
      <artifactId>playwright-core</artifactId>
      <version>1.35.0</version>
    </dependency>
```

And the test code worked without amendment.

```
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaywrightTest {
    private Browser browser;
    private Page page;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterEach
    public void tearDown() {
        if (page != null) page.close();
        if (browser != null) browser.close();
    }

    @Test
    public void testPageTitle() {
        page.navigate("https://testpages.eviltester.com");
        String title = page.title();
        System.out.println("Page Title: " + title);
        assertEquals("EvilTester", title, "Page title should be EvilTester");
    }
}
```

This first a little outside of my graphics card `2%/98% CPU/GPU `


## Cogito:8b

https://ollama.com/library/cogito

The playwright dependency was incorrect:

```
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright-chromium</artifactId>
        <version>1.28.0</version>
        <scope>test</scope>
    </dependency>
```

The test was... different.

```
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.Assertion.expect;

public class PlaywrightTest {
    
    @Test
    public void testPlaywright() throws Exception {
        // Launch Chrome browser using Playwright
        var playwright = new com.microsoft.playwright.Playwright();
        
        // Open the specified URL
        var page = playwright.chromium().launch().newPage();
        page.navigate("https://testpages.eviltester.com");
        
        // Assert that the page title contains "TestPages"
        expect(page.title()).toContain("TestPages");
        
        // Close the browser after test
        playwright.close();
    }
}

```

Playwright can not be instantiated like this `var playwright = new com.microsoft.playwright.Playwright();` and even if it could, I'd prefer to have seen it imported.

I'm not sure what the `expect` is - this may have been confused with some Javascript code int he model.

The model does work completely within my GPU

---

I tried cogito in a new chat and it generated a WebDriver test and project.

## Cogito:14b

Generated code for a wrong, but different, dependency:

```
        <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright-java</artifactId>
            <version>${playwright.version}</version>
        </dependency>
```

The test was also different:

```
    @Test
    public void testEvilTesterPage() {
        // Initialize playwright
        try (Playwright playwright = Playwright.create()) {
            // Launch browser
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)); // Set to true if you want to run in headless mode

            // Open new page
            Page page = browser.newPage();

            // Navigate to the URL
            page.navigate("https://testpages.eviltester.com");

            // You can add assertions here, for example:
            assertEquals(200, page.status(), "Status code should be 200");
            
            // Close browser
            browser.close();
        }
    }
```

There is no 'status' method on the page.

Wrapping assertions in a `try` would mean that they would always path. And there is no `catch` clause for the try.

Altogether, not a nice coding output from cogito in this instance.

## starcoder2

https://ollama.com/library/starcoder2

starcoder2:7b

This didn't actually generate any code.

```
The test will click on the button with the id of 'btn' and
then take a screenshot and save it to a folder called images
```

Very odd.

---

I tried starcoder2:15b

and still no code

```
a Java maven project using Junit 5 which has a single test that
uses playwright to open https://testpages.eviltester.com in a Chrome browser
```

## deepcoder

deepcoder:14b

6%/94% CPU/GPU


https://ollama.com/library/deepcoder

> create a Java maven project using Junit 5 which has a single test that uses playwright to open https://testpages.eviltester.com in a Chrome browser

Dependencies were wrong:

```
        <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright-core</artifactId>
            <version>1.37.0</version>
        </dependency>
        <dependency>
            <groupId>com.microsoft.playwright</groupId>
            <artifactId>playwright-chromium</artifactId>
            <version>1.37.0</version>
        </dependency>
```

And the test seemed to hallucinate the playwright api:

```
package com.example.playwright;

import com.microsoft.playwright.chromium.ChromiumBrowser;
import com.microsoft.playwright.chromium.options.ChromiumOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.Assertions.*;

public class PlaywrightTest {

    private ChromiumBrowser browser;

    @BeforeEach
    void setup() {
        // Create a new browser instance before each test
        browser = ChromiumBrowser.launch(ChromiumOptions.create()
                .setHeadless(false)  // Run in headful mode to see the browser
                .setSlowMo(100));   // Slow down the browser by 100ms for better observation
    }

    @Test
    void testEvilTesterPage() {
        // Navigate to the eviltester page
        browser.navigate("https://testpages.eviltester.com");

        // You can add assertions here using Playwright's built-in assertion methods
        // For example, check if the title contains "Evil Tester":
        String pageTitle = browser.getTitle();
        assertThat(pageTitle).contains("Evil Tester");
    }

    @Test
    void testMultiplePages() {
        // Navigate to multiple pages or perform other actions
        browser.navigate("https://testpages.eviltester.com/ajax.php");
        // Add assertions here...
    }
}

```