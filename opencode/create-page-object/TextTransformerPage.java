package com.eviltester.abstractions.apps.textTransformer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.eviltester.abstractions.config.SiteConfig;

public class TextTransformerPage {

    private final WebDriver driver;

    public TextTransformerPage(final WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(SiteConfig.SITE_DOMAIN + "/apps/text-transformer/");
    }

    public TextTransformerPage setInputText(String text) {
        driver.findElement(By.id("inputText")).clear();
        driver.findElement(By.id("inputText")).sendKeys(text);
        return this;
    }

    public String getInputText() {
        return driver.findElement(By.id("inputText")).getAttribute("value");
    }

    public String getReverseResult() {
        return driver.findElement(By.id("reverse")).getText();
    }

    public String getPigLatinResult() {
        return driver.findElement(By.id("pig-latin")).getText();
    }

    public String getRot13Result() {
        return driver.findElement(By.id("rot13")).getText();
    }

    public String getLeetSpeakResult() {
        return driver.findElement(By.id("l33t-speak")).getText();
    }

    public String getWordShufflerResult() {
        return driver.findElement(By.id("word-shuffler")).getText();
    }

    public String getAcronymCreatorResult() {
        return driver.findElement(By.id("acronym-creator")).getText();
    }

    public String getSpongebobCaseResult() {
        return driver.findElement(By.id("spongebob-case")).getText();
    }

    public String getMorseCodeResult() {
        return driver.findElement(By.id("morse-code")).getText();
    }

    public String getPirateSpeakResult() {
        return driver.findElement(By.id("pirate-speak")).getText();
    }

    public String getLastVisitDetails() {
        return driver.findElement(By.id("last-visit-details")).getText();
    }
}