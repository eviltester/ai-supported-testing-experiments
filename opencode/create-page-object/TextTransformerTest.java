package com.eviltester.apps.textTransformer;

import com.eviltester.abstractions.apps.textTransformer.TextTransformerPage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class TextTransformerTest {

    private static WebDriver driver;
    private TextTransformerPage textTransformerPage;

    @BeforeAll
    public static void setupClass() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void setup() {
        textTransformerPage = new TextTransformerPage(driver);
        textTransformerPage.open();
    }

    @Test
    public void canTransformTextToReverse() {
        textTransformerPage.setInputText("Hello World");

        Assertions.assertEquals("dlroW olleH", textTransformerPage.getReverseResult());
    }

    @Test
    public void canTransformTextToPigLatin() {
        textTransformerPage.setInputText("Hello World");

        Assertions.assertEquals("elloHay orldWay", textTransformerPage.getPigLatinResult());
    }

    @Test
    public void canTransformTextToRot13() {
        textTransformerPage.setInputText("Hello World");

        Assertions.assertEquals("Uryyb Jbeyq", textTransformerPage.getRot13Result());
    }

    @Test
    public void canTransformTextToLeetSpeak() {
        textTransformerPage.setInputText("Hello World");

        Assertions.assertEquals("H3110 W0r1d", textTransformerPage.getLeetSpeakResult());
    }

    @Test
    public void canCreateAcronym() {
        textTransformerPage.setInputText("Hello World Test");

        Assertions.assertEquals("HWT", textTransformerPage.getAcronymCreatorResult());
    }

    @Test
    public void canTransformToSpongebobCase() {
        textTransformerPage.setInputText("hello");

        Assertions.assertEquals("hElLo", textTransformerPage.getSpongebobCaseResult());
    }

    @Test
    public void canTransformToMorseCode() {
        textTransformerPage.setInputText("hi");

        Assertions.assertEquals(".... ..", textTransformerPage.getMorseCodeResult());
    }

    @Test
    public void canTransformToPirateSpeak() {
        textTransformerPage.setInputText("Hello my friend");

        Assertions.assertEquals("ahoy me matey, arr!", textTransformerPage.getPirateSpeakResult());
    }

    @Test
    public void canShuffleWords() {
        String input = "Hello World Test";
        textTransformerPage.setInputText(input);

        String result = textTransformerPage.getWordShufflerResult();

        // Check that all words are present (order may vary due to shuffling)
        String[] inputWords = input.split(" ");
        String[] resultWords = result.split(" ");

        Assertions.assertEquals(inputWords.length, resultWords.length);

        // Sort both arrays and compare to ensure same words
        Arrays.sort(inputWords);
        Arrays.sort(resultWords);
        Assertions.assertArrayEquals(inputWords, resultWords);
    }

    @Test
    public void lastVisitDetailsAreDisplayed() {
        String lastVisit = textTransformerPage.getLastVisitDetails();

        Assertions.assertTrue(lastVisit.contains("Usage") || lastVisit.contains("visit"));
    }

    @Test
    public void firstVisitShowsCorrectMessage() {
        String lastVisit = textTransformerPage.getLastVisitDetails();

        Assertions.assertEquals("This is your first Usage.", lastVisit);
    }

    @Test
    public void lastVisitDetailsShowPreviousTextAfterRefresh() throws ParseException {
        // Enter some text to transform
        String testText = "Test transformation text";
        textTransformerPage.setInputText(testText);

        // Record time before refresh
        Instant beforeRefresh = Instant.now();

        // Refresh the page
        driver.navigate().refresh();

        // Check that last visit details now show the previous text and recent time
        String lastVisit = textTransformerPage.getLastVisitDetails();

        Assertions.assertTrue(lastVisit.contains(testText));

        // Extract and check the date is recent (within 3 seconds)
        int start = lastVisit.indexOf("was on ") + 7;
        int end = lastVisit.indexOf(" and you");
        String dateStr = lastVisit.substring(start, end);
        // Remove the (Coordinated Universal Time) part
        int parenIndex = dateStr.indexOf(" (");
        if (parenIndex > 0) {
            dateStr = dateStr.substring(0, parenIndex);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH);
        Date parsedDate = sdf.parse(dateStr);
        Instant parsedInstant = parsedDate.toInstant();

        Duration diff = Duration.between(parsedInstant, beforeRefresh);
        Assertions.assertTrue(Math.abs(diff.getSeconds()) < 3, "Date in last visit should be within 3 seconds of refresh time");
    }

    @AfterAll
    public static void teardownClass() {
        driver.quit();
    }
}