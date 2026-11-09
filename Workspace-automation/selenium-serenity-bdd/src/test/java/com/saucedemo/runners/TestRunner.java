package com.saucedemo.runners;


import org.junit.platform.suite.api.*;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Serenity + Cucumber Test Runner.
 * Maven Surefire is configured (see pom.xml) to run classes matching **/
/*@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.saucedemo.stepdefinitions")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "io.cucumber.core.plugin.SerenityReporterParallel, pretty, timeline:target/test-results/timelin")*/
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.saucedemo.stepdefinitions"
)
@ConfigurationParameter(
        // SerenityReporterParallel is thread-safe and recommended even for
        // non-parallel runs. Note the package changed in Serenity 5.0.0:
        // io.cucumber.core.plugin.* -> net.serenitybdd.cucumber.core.plugin.*
        key = PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel, " +
                "pretty, " +
                "json:target/cucumber-reports/cucumber.json, " +
                "html:target/cucumber-reports/cucumber.html"
)
public class TestRunner {
}
