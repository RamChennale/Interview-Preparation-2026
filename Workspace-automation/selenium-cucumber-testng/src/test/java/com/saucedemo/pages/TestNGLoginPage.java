package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class TestNGLoginPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    @CacheLookup
    private WebElement home;


    public TestNGLoginPage(WebDriver driver) {
        this.driver=driver;
    }

    public void verifyHomePage() {
        SoftAssert softAssert= new SoftAssert();
        Actions actions= new Actions(driver);
        softAssert.assertEquals(home.getText(), "Products", "Failed to display Home menu");
        actions.moveToElement(home).perform();

        softAssert.assertAll();
        System.out.println("All menus are verified");
    }
}

