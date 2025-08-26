package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.Map;

public class CalculatorPage {

    private final Page page;
    private final Locator display;
    private final Map<String, Locator> buttons = new HashMap<>();

    public CalculatorPage(Page page) {
        this.page = page;
        this.display = page.locator("#calculated-display");

        // Digit buttons 0–9
        buttons.put("0", page.locator("#button00"));
        buttons.put("1", page.locator("#button01"));
        buttons.put("2", page.locator("#button02"));
        buttons.put("3", page.locator("#button03"));
        buttons.put("4", page.locator("#button04"));
        buttons.put("5", page.locator("#button05"));
        buttons.put("6", page.locator("#button06"));
        buttons.put("7", page.locator("#button07"));
        buttons.put("8", page.locator("#button08"));
        buttons.put("9", page.locator("#button09"));

        // Operators
        buttons.put("+", page.locator("#buttonplus"));
        buttons.put("-", page.locator("#buttonminus"));
        buttons.put("*", page.locator("#buttonmultiply"));
        buttons.put("/", page.locator("#buttondivide"));

        // Equals and Clear
        buttons.put("=", page.locator("#buttonequals"));
        buttons.put("CE", page.locator("#buttonclearentry"));
    }

    /** Navigate to calculator page */
    public void navigate() {
        page.navigate("https://testpages.eviltester.com/styled/apps/calculator.html");
    }

    /** Press any supported button by its label */
    public void press(String key) {
        if (!buttons.containsKey(key)) {
            throw new IllegalArgumentException("Unsupported button: " + key);
        }

        Locator button = buttons.get(key);
        // highlight the button on screen prior to clicking, could be useful for video recording
//        page.evaluate(
//                "(el) => { el.style.border='3px solid red'; setTimeout(()=>el.style.border='',300); }",
//                button.elementHandle()
//        );
        button.click();
    }

    /** Get the current value shown on the calculator display */
    public String getDisplayValue() {
        return display.inputValue().trim();
    }

    /** Convenience: perform a full calculation sequence like "1+2=" */
    public String calculate(String sequence) {
        for (char c : sequence.toCharArray()) {
            press(String.valueOf(c));
        }
        return getDisplayValue();
    }
}
