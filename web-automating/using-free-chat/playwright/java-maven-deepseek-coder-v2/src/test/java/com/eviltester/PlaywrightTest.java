package com.eviltester;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PlaywrightTest {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = com.microsoft.playwright.Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
    }

    @Test
    public void testOpenURL() {
        // Navigate to the URL
        page.navigate("https://testpages.eviltester.com/styled/apps/calculator.html");

        // Add assertions or verifications here if needed
        String title = page.title();
        Assertions.assertEquals("Calculator",title);
    }

    @AfterAll
    public static void closeBrowser() {
        browser.close();
        playwright.close();
    }
}

