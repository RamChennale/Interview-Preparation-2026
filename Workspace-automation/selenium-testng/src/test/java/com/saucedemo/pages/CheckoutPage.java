package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object representing the SauceDemo Checkout flow:
 *  - Step One: Your Information (first name, last name, zip/postal code)
 *  - Step Two: Overview (Finish button)
 *  - Complete: Order confirmation
 */
public class CheckoutPage extends BasePage {

    // ---- Step One: Your Information ----
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement zipCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement checkoutErrorMessage;

    // ---- Step Two: Overview ----
    @FindBy(id = "finish")
    private WebElement finishButton;

    // ---- Complete: Confirmation ----
    @FindBy(css = ".complete-header")
    private WebElement orderConfirmationMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterCheckoutInformation(String firstName, String lastName, String zipCode) {
        waitUntilVisible(firstNameField).sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        zipCodeField.sendKeys(zipCode);
    }

    public void clickContinue() {
        waitUntilClickable(continueButton).click();
    }

    public void clickFinish() {
        waitUntilClickable(finishButton).click();
    }

    public String getOrderConfirmationMessage() {
        return waitUntilVisible(orderConfirmationMessage).getText();
    }

    public boolean isOrderConfirmationDisplayed() {
        return waitUntilVisible(orderConfirmationMessage).isDisplayed();
    }
}
