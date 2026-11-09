package com.saucedemo.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

//import net.thucydides.core.annotations.DefaultUrl;


/**
 * Page Object representing the SauceDemo Login page.
 * URL: https://www.saucedemo.com/
 */
@DefaultUrl("https://www.saucedemo.com/")
public class LoginPage extends PageObject {

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElementFacade usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElementFacade passwordField;

    @FindBy(id = "login-button")
    private WebElementFacade loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElementFacade errorMessage;

    @Step
    public void openApplication() {
        getDriver().get("https://www.saucedemo.com/");
        getDriver().manage().window().maximize();
        waitABit(5000);
    }

    @Step
    public void enterUsername(String username) {
        usernameField.waitUntilVisible();
        usernameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.waitUntilVisible();
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.waitUntilClickable();
        loginButton.click();
    }

    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorDisplayed() {
        return errorMessage.isCurrentlyVisible();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
