package com.example;

import com.example.pages.CalculatorPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class PlaywrightTest {

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false) // set true if you want headless
                .setChannel("chrome")); // ensure Chrome, not Chromium // "msedge"
    }

    @AfterAll
    static void tearDownAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void cleanup() {
        context.close();
    }

    @Test
    void openTestPagesCalculator() {
        page.navigate("https://testpages.eviltester.com/styled/apps/calculator.html");
        Assertions.assertEquals(page.title(),"Calculator");
    }

    @Test
    void testCalculatorAddition() {
        // Navigate to the calculator page
        page.navigate("https://testpages.eviltester.com/styled/apps/calculator.html");

        // Click 1 + 2 =
        page.click("button#button01"); // 1
        page.click("button#buttonplus"); // +
        page.click("button#button02"); // 2
        page.click("button#buttonequals"); // =

        // Get the result from the calculator
        String result = page.locator("#calculated-display").inputValue();

        // Assert the result is "3"
        Assertions.assertEquals("3", result.trim());
    }

    @Test
    void testCalculatorAdditionWithPageObject() {
        // Create the page object
        CalculatorPage calculator = new CalculatorPage(page);

        // Navigate to the calculator page
        calculator.navigate();

        // Perform 1 + 2 =
        calculator.press("1");
        calculator.press("+");
        calculator.press("2");
        calculator.press("=");

        // Get the result from the calculator display
        String result = calculator.getDisplayValue();

        // Assert the result is "3"
        Assertions.assertEquals("3", result.trim());
    }

}
