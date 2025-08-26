package com.example;

import com.example.pages.CalculatorPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorDataDrivenTest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("chrome")
                        // enable to slow every action in the test down
                        // could be useful when recording a video
                        // .setSlowMo(1000)
        );
    }

    @AfterAll
    static void teardownAll() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void setup() {
        context = browser.newContext(
                // enable video recording
//                new Browser.NewContextOptions()
//                .setRecordVideoDir(Paths.get("videos/"))
        );
        page = context.newPage();

    }

    @AfterEach
    void cleanup() {
        context.close();
    }

    // --- DATA PROVIDER ---
    static Stream<org.junit.jupiter.params.provider.Arguments> calculatorCases() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(
                        new String[]{"1", "+", "2", "="}, "3"),
                org.junit.jupiter.params.provider.Arguments.of(
                        new String[]{"7", "*", "8", "="}, "56"),
                org.junit.jupiter.params.provider.Arguments.of(
                        new String[]{"9", "-", "5", "="}, "4"),
                org.junit.jupiter.params.provider.Arguments.of(
                        new String[]{"8", "/", "2", "="}, "4")
        );
    }

    // --- PARAMETERIZED TEST ---
    @ParameterizedTest
    @MethodSource("calculatorCases")
    void testCalculatorWithData(String[] keys, String expected) {
        CalculatorPage calc = new CalculatorPage(page);
        calc.navigate();

        // Press each supplied key
        for (String key : keys) {
            calc.press(key);
        }

        // Assert the display matches the expected result
        assertEquals(expected, calc.getDisplayValue());
    }
}
