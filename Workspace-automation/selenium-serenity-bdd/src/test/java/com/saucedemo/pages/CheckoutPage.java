package com.saucedemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

/**
 * Page Object representing the SauceDemo Checkout flow:
 *  - Step One: Your Information (first name, last name, zip/postal code)
 *  - Step Two: Overview (Finish button)
 *  - Complete: Order confirmation
 */
public class CheckoutPage extends PageObject {

    // ---- Step One: Your Information ----
    @FindBy(id = "first-name")
    private WebElementFacade firstNameField;

    @FindBy(id = "last-name")
    private WebElementFacade lastNameField;

    @FindBy(id = "postal-code")
    private WebElementFacade zipCodeField;

    @FindBy(id = "continue")
    private WebElementFacade continueButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElementFacade checkoutErrorMessage;

    // ---- Step Two: Overview ----
    @FindBy(id = "finish")
    private WebElementFacade finishButton;

    // ---- Complete: Confirmation ----
    @FindBy(css = ".complete-header")
    private WebElementFacade orderConfirmationMessage;

    public void enterCheckoutInformation(String firstName, String lastName, String zipCode) {
        firstNameField.waitUntilVisible();
        firstNameField.type(firstName);
        lastNameField.type(lastName);
        zipCodeField.type(zipCode);
    }

    public void clickContinue() {
        continueButton.waitUntilClickable();
        continueButton.click();
    }

    public void clickFinish() {
        finishButton.waitUntilClickable();
        finishButton.click();
    }

    public String getOrderConfirmationMessage() {
        orderConfirmationMessage.waitUntilVisible();
        return orderConfirmationMessage.getText();
    }

    public boolean isOrderConfirmationDisplayed() {
        return orderConfirmationMessage.isCurrentlyVisible();
    }
}
