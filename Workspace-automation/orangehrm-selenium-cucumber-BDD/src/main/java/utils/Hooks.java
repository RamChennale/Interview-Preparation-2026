package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


/**
 * Runs before/after EVERY scenario, regardless of which feature file it's in.
 * This is the single place that starts and stops the browser, so step
 * definitions never have to worry about driver lifecycle themselves.
 */
public class Hooks {

        private final TestContext context;
        private static final ExtentReports extent = ExtentManager.getInstance();
        private ExtentTest test;

        public Hooks(TestContext context) {
            this.context = context;
        }

        @Before
        public void setUp(Scenario scenario) {
            context.setDriver(DriverManager.getDriver());
            test = extent.createTest(scenario.getName());
        }

        @After
        public void tearDown(Scenario scenario) {
            if (scenario.isFailed()) {
                test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
            } else {
                test.log(Status.PASS, "Scenario passed: " + scenario.getName());
            }
            ExtentManager.flush();
            DriverManager.quitDriver();
        }

    }
