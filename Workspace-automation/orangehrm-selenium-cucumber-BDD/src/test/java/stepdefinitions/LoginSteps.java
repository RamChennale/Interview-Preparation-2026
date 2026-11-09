package stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestContext;

/**
 * Step definitions for the Login feature.
 */
public class LoginSteps {

    private final TestContext context;
    private LoginPage loginPage;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("user launches the OrangeHRM application")
    public void user_launches_the_orangehrm_application() {
        context.getDriver().get(ConfigReader.get("url"));
        loginPage = new LoginPage(context.getDriver());
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername(ConfigReader.get("username"));
        loginPage.enterPassword(ConfigReader.get("password"));
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("user clicks on the login button")
    public void user_clicks_on_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("user should be navigated to the dashboard page")
    public void user_should_be_navigated_to_the_dashboard_page() {
        Assert.assertTrue("Dashboard was not displayed after login", loginPage.isDashboardDisplayed());
    }

    @Then("user should see an error message {string}")
    public void user_should_see_an_error_message(String expectedMessage) {
        Assert.assertTrue(
                "Expected error message not displayed",
                loginPage.getErrorMessage().contains(expectedMessage));
    }

    @When("On click of Logout button use should be logged out successfully")
    public void onClickOfLogoutButtonUseShouldBeLoggedOutSuccessfully() {
        loginPage.logOut();
    }
}
