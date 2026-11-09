package com.bdd.pom.stepdefinitions;

import com.bdd.pom.base.DriverManager;
import com.bdd.pom.pages.DashboardPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardSteps {

    @When("I click the {string} menu")
    public void iClickTheMenu(String menuName) {
        new DashboardPage(DriverManager.getDriver()).clickMenu(menuName);
    }

    @Then("the page header should be {string}")
    public void thePageHeaderShouldBe(String expectedHeader) {
        String actual = new DashboardPage(DriverManager.getDriver()).getPageHeader();
        Assert.assertEquals(actual, expectedHeader);
    }
}
