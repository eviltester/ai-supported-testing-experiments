package com.eviltester;

import com.eviltester.pages.CalculatorPage;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private static com.microsoft.playwright.Playwright playwright;
    private static com.microsoft.playwright.Browser browser;
    private static Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = com.microsoft.playwright.Playwright.create();
        browser = playwright.chromium().launch();
        page = browser.newPage();
        // Navigate to the calculator URL
        page.navigate("https://testpages.eviltester.com/styled/apps/calculator.html");
    }

    @Test
    public void testCalculator() {
        // Click on the 1 button
        page.locator("#button01").click();

        // Click on the plus button
        page.locator("#buttonplus").click();

        // Click on the 2 button
        page.locator("#button02").click();

        // Click on the equal button
        page.locator("#buttonequals").click();

        // Assert that the result is 3
        String result = page.inputValue("#calculated-display");
        Assertions.assertEquals("3", result);
    }

    @Test
    public void testCalculatorWithPageObject() {
        CalculatorPage calculatorPage = new CalculatorPage(page);
        // Perform actions using the page object methods
        calculatorPage.clickDigitOne();
        calculatorPage.clickOperatorPlus();
        calculatorPage.clickDigitTwo();
        calculatorPage.clickCalculate();

        // Get and assert the result
        String result = calculatorPage.getResultDisplayedValue();
        Assertions.assertEquals("3", result);
    }

    @AfterAll
    public static void closeBrowser() {
        browser.close();
        playwright.close();
    }
}
