package com.saucedemo.stepdefinitions;

import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step Definitions for src/test/resources/features/checkout.feature.
 * All UI interaction is delegated to Page Objects (POM); this class only
 * orchestrates calls and performs TestNG assertions.
 */
public class CheckoutStepDefinitions {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    // -------------------- Background / Common Login --------------------

    @Given("I launch the SauceDemo application")
    public void iLaunchTheSauceDemoApplication() {
        loginPage = new LoginPage(DriverManager.getDriver());
        String baseUrl = ConfigReader.get("base.url", "https://www.saucedemo.com/");
        loginPage.openApplication(baseUrl);
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.loginAs(username, password);
        productsPage = new ProductsPage(DriverManager.getDriver());
    }

 /*   @Then("I should be logged in successfully and land on the products page")
    public void iShouldBeLoggedInSuccessfullyAndLandOnTheProductsPage() {
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "Products page was not displayed after login");
    }*/

    // -------------------- Add to Cart --------------------

    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        productsPage.addProductToCart(productName);
    }

    @Then("the cart icon should show {string} item")
    public void theCartIconShouldShowItem(String expectedCount) {
        Assert.assertEquals(productsPage.getCartItemCount(), expectedCount,
                "Cart item count does not match expected value");
    }

    @When("I click on the cart icon")
    public void iClickOnTheCartIcon() {
        productsPage.clickCartIcon();
        cartPage = new CartPage(DriverManager.getDriver());
    }

    @Then("{string} should be present in the cart")
    public void shouldBePresentInTheCart(String productName) {
        Assert.assertTrue(cartPage.isItemInCart(productName),
                productName + " was not found in the cart");
    }

    // -------------------- Checkout --------------------

    @When("I click on the Checkout button")
    public void iClickOnTheCheckoutButton() {
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
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
        Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed(),
                "Order confirmation message was not displayed");
        Assert.assertEquals(checkoutPage.getOrderConfirmationMessage(), expectedMessage,
                "Order confirmation message text does not match");
    }
}
