package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.TestContext;

public class Hooks {

    private final TestContext testContext;

    // Injected by cucumber-picocontainer - same instance shared across all step classes
    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");
        testContext.setPage(testContext.getPlaywrightFactory().initBrowser(browser));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && testContext.getPage() != null) {
            byte[] screenshot = testContext.getPage().screenshot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        testContext.getPlaywrightFactory().tearDown();
    }
}
