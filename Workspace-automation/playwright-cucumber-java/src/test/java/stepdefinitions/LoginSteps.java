package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestContext;

public class LoginSteps {

    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;

    public LoginSteps(TestContext testContext) {
        this.loginPage = new LoginPage(testContext.getPage());
        this.inventoryPage = new InventoryPage(testContext.getPage());
    }

    @Given("user launches the SauceDemo application")
    public void user_launches_the_saucedemo_application() {

        loginPage.navigateTo(ConfigReader.getProperty("baseUrl"));
    }

    @When("user logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("user should be navigated to the products page")
    public void user_should_be_navigated_to_the_products_page() {
        Assert.assertTrue("User was not navigated to the inventory/products page after login",
                inventoryPage.isOnInventoryPage());
    }
}
