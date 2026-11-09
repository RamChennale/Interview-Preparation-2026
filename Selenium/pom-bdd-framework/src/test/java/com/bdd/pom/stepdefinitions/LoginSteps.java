package com.bdd.pom.stepdefinitions;

import com.bdd.pom.base.DriverManager;
import com.bdd.pom.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("I am on the OrangeHRM login page")
    public void iAmOnTheLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.open();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWith(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the dashboard")
    public void iShouldSeeTheDashboard() {
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Dashboard was not displayed after login");
    }
}
