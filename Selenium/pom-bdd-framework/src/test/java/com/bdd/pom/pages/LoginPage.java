package com.bdd.pom.pages;

import com.bdd.pom.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private static final String URL =
            "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton   = By.xpath("//button[@type='submit']");
    private final By errorMessage  = By.cssSelector("p.oxd-alert-content-text");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void login(String username, String password) {
        enterInput(usernameField, username);
        enterInput(passwordField, password);
        click(loginButton);
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(ExpectedConditions.urlContains("dashboard"));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
