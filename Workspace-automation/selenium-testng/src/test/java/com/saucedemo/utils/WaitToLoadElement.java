package com.saucedemo.utils;

import com.saucedemo.hooks.TestNGBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitToLoadElement extends TestNGBaseTest {
    static WebDriverWait webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(20));

    public static WebElement waitToLoadElement(WebElement webElement) {
        WebDriverWait webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(20));
        return webDriverWait.until(ExpectedConditions.visibilityOf(webElement) );
    }

    public static List<WebElement> waitToLoadAllElements(List<WebElement> webElementList) {
        WebDriverWait webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(20));
        return webDriverWait.until(ExpectedConditions.visibilityOfAllElements((webElementList)));

    }
    public static void waitToSelectDropDownValue(WebElement webElement) {
        WebDriverWait webDriverWait= new WebDriverWait(driver, Duration.ofSeconds(20));
        webDriverWait.until(ExpectedConditions.elementToBeSelected(webElement));
    }

    public static void waitToPopUpAlert() {
        webDriverWait.until(ExpectedConditions.alertIsPresent());
    }
}
