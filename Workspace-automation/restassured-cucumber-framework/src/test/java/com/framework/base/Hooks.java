package com.framework.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cucumber lifecycle hooks. BaseClass is injected via PicoContainer -
 * the same instance is shared with the step-definition classes for this scenario.
 */
public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private final BaseClass baseClass;

    public Hooks(BaseClass baseClass) {
        this.baseClass = baseClass;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        logger.info("=========== STARTING SCENARIO: {} ===========", scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (baseClass.getResponse() != null) {
            // Attach raw response body to the Cucumber report on failure for fast debugging
            if (scenario.isFailed()) {
                scenario.attach(baseClass.getResponse().getBody().asString(),
                        "application/json", "FailedResponseBody");
            }
        }
        logger.info("=========== FINISHED SCENARIO: {} | STATUS: {} ===========",
                scenario.getName(), scenario.getStatus());
    }
}
