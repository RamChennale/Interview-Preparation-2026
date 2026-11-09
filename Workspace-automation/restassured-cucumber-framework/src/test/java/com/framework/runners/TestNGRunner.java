package com.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * Entry point that bridges Cucumber to TestNG (the requested execution engine).
 * Extends AbstractTestNGCucumberTests to get TestNG @Test methods generated per scenario.
 *
 * Tag filter is read from -Dcucumber.filter.tags (defaults to @Regression in pom.xml),
 * so Jenkins can trigger different subsets (@Smoke, @Regression, @Create, etc.)
 * without recompiling.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.framework.stepdefinitions"},
        // Default tag; the "cucumber.filter.tags" JVM system property (set via
        // Maven surefire / Jenkins -D flag) automatically overrides this at runtime.
        tags = "@GetSingleUser",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        publish = false

)
public class TestNGRunner extends AbstractTestNGCucumberTests {

    // Override to enable parallel scenario execution across threads (TestNG DataProvider)
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
