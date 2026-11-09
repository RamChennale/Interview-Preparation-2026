package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import net.thucydides.model.reports.adaptors.specflow.ScenarioStep;
import org.junit.Assert;

/**
 * Step Definitions for the SauceDemo checkout.feature.
 * Delegates all UI interaction to the Page Objects (POM) via Serenity's @Steps.
 */
public class CheckoutStepDefinitions  {

    @Steps
    LoginPage loginPage;

    @Steps
    ProductsPage productsPage;

    @Steps
    CartPage cartPage;

    @Steps
    CheckoutPage checkoutPage;

    // -------------------- Background / Common Login --------------------

    @Given("I launch the SauceDemo application")
    public void iLaunchTheSauceDemoApplication() {

        loginPage.openApplication();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.loginAs(username, password);
    }

    @Then("I should be logged in successfully and land on the products page")
    public void iShouldBeLoggedInSuccessfullyAndLandOnTheProductsPage() {
        Assert.assertTrue("Products page was not displayed after login",
                productsPage.isProductsPageDisplayed());
    }

    // -------------------- Add to Cart --------------------

    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        productsPage.addProductToCart(productName);
        Serenity.recordReportData().withTitle("Product Added").andContents(productName);
    }

    @Then("the cart icon should show {string} item")
    public void theCartIconShouldShowItem(String expectedCount) {
        Assert.assertEquals("Cart item count does not match expected value",
                expectedCount, productsPage.getCartItemCount());
    }

    @When("I click on the cart icon")
    public void iClickOnTheCartIcon() {
        productsPage.clickCartIcon();
    }

    @Then("{string} should be present in the cart")
    public void shouldBePresentInTheCart(String productName) {
        Assert.assertTrue(productName + " was not found in the cart",
                cartPage.isItemInCart(productName));
    }

    // -------------------- Checkout --------------------

    @When("I click on the Checkout button")
    public void iClickOnTheCheckoutButton() {
        cartPage.clickCheckoutButton();
    }

    @And("I enter checkout information with first name {string}, last name {string} and zip code {string}")
    public void iEnterCheckoutInformationWithFirstNameLastNameAndZipCode(String firstName, String lastName, String zipCode) {
        checkoutPage.enterCheckoutInformation(firstName, lastName, zipCode);
    }

    @And("I click Continue")
    public void iClickContinue() {
        checkoutPage.clickContinue();
    }

    @And("I click Finish")
    public void iClickFinish() {
        checkoutPage.clickFinish();
    }

    @Then("the order confirmation message {string} should be displayed")
    public void theOrderConfirmationMessageShouldBeDisplayed(String expectedMessage) {
        Assert.assertTrue("Order confirmation message was not displayed",
                checkoutPage.isOrderConfirmationDisplayed());
        Assert.assertEquals("Order confirmation message text does not match",
                expectedMessage, checkoutPage.getOrderConfirmationMessage());
    }
}
