package com.eviltester.pages;

import com.microsoft.playwright.Page;

public class CalculatorPage {
    private final Page page;

    // Locators for the calculator buttons and display
    private String digit1 = "#button01";
    private String operatorPlus = "#buttonplus";
    private String digit2 = "#button02";
    private String calculateButton = "#buttonequals";
    private String resultDisplay = "#calculated-display";

    public CalculatorPage(Page page) {
        this.page = page;
    }

    // Method to enter the number 1
    public void clickDigitOne() {
        page.locator(digit1).click();
    }

    // Method to add a digit (similarly for other digits and operators)
    public void clickOperatorPlus() {
        page.locator(operatorPlus).click();
    }

    // Method to enter the number 2
    public void clickDigitTwo() {
        page.locator(digit2).click();
    }

    // Method to calculate the result
    public void clickCalculate() {
        page.locator(calculateButton).click();
    }

    // Method to get the displayed result
    public String getResultDisplayedValue() {
        return page.inputValue(resultDisplay);
    }
}

