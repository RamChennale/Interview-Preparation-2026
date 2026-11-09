package com.saucedemo.runners;

import com.saucedemo.utils.ConfigReader;
import com.vimalselvam.cucumber.listener.Reporter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import java.io.File;



/**
 * Cucumber + TestNG Test Runner.
 *
 * Extending AbstractTestNGCucumberTests turns every Cucumber scenario into its own
 * TestNG @Test, so scenarios show up individually in TestNG/Surefire/Jenkins reports
 * and can be executed in parallel (see the "dataprovider" thread count in testng.xml).
 *
 * NOTE: "tags" is intentionally NOT set here. Values set via @CucumberOptions take
 * precedence over system properties/environment variables/cucumber.properties, which
 * would make -Dcucumber.filter.tags impossible to override from Maven/Jenkins. Instead,
 * the default tag expression lives in src/test/resources/cucumber.properties, and
 * Surefire forwards -Dcucumber.filter.tags as a system property that overrides it, e.g.:
 *   mvn clean test -Dcucumber.filter.tags="@smoke"
 */

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.saucedemo.stepdefinitions", "com.saucedemo.hooks"},
        tags = "regression",
        plugin = {
                "pretty",
                "json:target/cucumber-reports/cucumber.json",
                "html:target/cucumber-reports/cucumber.html",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class TestRunner extends AbstractTestNGCucumberTests {

    /**
     * Runs scenarios in parallel across multiple threads.
     * Safe here because DriverManager uses a ThreadLocal<WebDriver>.
     */

    public boolean runScenariosInParallel() {
        return false; // set to true + configure dataproviderthreadcount in testng.xml to parallelize
    }

    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(ConfigReader.getReportConfigPath()));
    }
}
