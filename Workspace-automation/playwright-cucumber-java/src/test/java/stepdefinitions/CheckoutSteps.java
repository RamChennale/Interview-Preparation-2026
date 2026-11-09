package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import utils.TestContext;

public class CheckoutSteps {

    private final InventoryPage inventoryPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;

    public CheckoutSteps(TestContext testContext) {
        this.inventoryPage = new InventoryPage(testContext.getPage());
        this.cartPage = new CartPage(testContext.getPage());
        this.checkoutPage = new CheckoutPage(testContext.getPage());
    }

    @When("user adds {string} to the cart")
    public void user_adds_to_the_cart(String productName) {
        inventoryPage.addProductToCart();
    }

    @When("user clicks on the cart icon")
    public void user_clicks_on_the_cart_icon() {
        inventoryPage.clickCartIcon();
    }

    @Then("the cart should contain {string}")
    public void the_cart_should_contain(String productName) {
        Assert.assertTrue("Expected product '" + productName + "' was not found in the cart",
                cartPage.isProductInCart(productName));
    }

    @When("user clicks on checkout button")
    public void user_clicks_on_checkout_button() {
        cartPage.clickCheckout();
    }

    @When("user enters checkout information {string} {string} {string}")
    public void user_enters_checkout_information(String firstName, String lastName, String zip) {
        checkoutPage.enterCheckoutInfo(firstName, lastName, zip);
    }

    @When("user clicks continue button")
    public void user_clicks_continue_button() {
        checkoutPage.clickContinue();
    }

    @When("user clicks finish button")
    public void user_clicks_finish_button() {
        checkoutPage.clickFinish();
    }

    @Then("user should see the message {string}")
    public void user_should_see_the_message(String expectedMessage) {
        Assert.assertEquals("Order confirmation message did not match",
                expectedMessage, checkoutPage.getOrderConfirmationMessage());
    }
}
