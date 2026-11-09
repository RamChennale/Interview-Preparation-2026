package com.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Entry point that ties Cucumber to TestNG.
 * Picks up .feature files and matches steps to methods in
 * com.example.stepdefs package.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.example.stepdefs"},
        tags = "@create",
        plugin = {"pretty",
                "summary",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"},
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
}
