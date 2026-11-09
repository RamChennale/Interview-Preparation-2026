#Page Object Model vs Page Factory — differences, and why you picked one.
/*
package com.selenium.A1_seleniumInterviewEx.selenium26.POMframework;

import com.selenium.A1_seleniumInterviewEx.selenium26.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.selenium.A1_seleniumInterviewEx.selenium26.base.DriverManager.wait;

public class POMPageWihtoutPageFactory extends TestBase {

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");

    public POMPageWihtoutPageFactory(WebDriver driver) {
        TestBase.driver = driver; 							// no PageFactory.initElements(driver, this)
         }

    private WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void enterUsername(String username) {
        waitVisible(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitVisible(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        waitVisible(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}
