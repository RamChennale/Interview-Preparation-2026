package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AdminUserPage;
import utils.TestContext;

/**
 * Step definitions for the Admin > User scenarios.
 */
public class AdminUserSteps {

    private final TestContext context;
    private AdminUserPage adminUserPage;
    private String generatedUsername;

    public AdminUserSteps(TestContext context) {
        this.context = context;
    }

    @Given("admin navigates to Admin module")
    public void admin_navigates_to_admin_module() {
        adminUserPage = new AdminUserPage(context.getDriver());
        adminUserPage.clickAdminMenu();
    }

    @When("admin clicks on Add button")
    public void admin_clicks_on_add_button() {
        adminUserPage.clickAddButton();
    }

    @And("admin selects user role {string}")
    public void admin_selects_user_role(String role) throws InterruptedException {
        adminUserPage.selectUserRole(role);
    }

    @And("admin enters employee name {string} and selects employee {string}")
    public void admin_enters_employee_name_and_selects_employee(String namePrefix, String employeeText) {
        adminUserPage.enterEmployeeName(namePrefix);
        adminUserPage.selectEmployeeFromSuggestion(employeeText);
    }

    @And("admin selects status {string}")
    public void admin_selects_status(String status) {
        adminUserPage.selectStatus(status);
    }

    @And("admin enters auto generated username and password")
    public void admin_enters_auto_generated_username_and_password() {
        Faker faker = new Faker();
        generatedUsername = faker.name().username();
        String generatedPassword = faker.internet().password(8, 16, true, true, true);

        System.out.println("Generated Username: " + generatedUsername);
        System.out.println("Generated Password: " + generatedPassword);

        adminUserPage.enterUsername(generatedUsername);
        adminUserPage.enterPassword(generatedPassword);
        adminUserPage.enterConfirmPassword(generatedPassword);
    }

    @And("admin clicks Save button")
    public void admin_clicks_save_button() {
        adminUserPage.clickSaveButton();
    }

    @Then("new user should be added successfully")
    public void new_user_should_be_added_successfully() {
        Assert.assertTrue("Success message was not displayed after saving the user",
                adminUserPage.isUserAddedSuccessfully());
    }

    @When("admin searches for username {string}")
    public void admin_searches_for_username(String username) {
        adminUserPage.searchByUsername(username);
    }

    @Then("search results should contain {string}")
    public void search_results_should_contain(String username) {
        System.out.println("username  : "+username);
        Assert.assertTrue("Search results did not contain expected username: " + username,
                adminUserPage.searchResultsContain(username));
    }
}
