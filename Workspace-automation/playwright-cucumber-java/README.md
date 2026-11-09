# SauceDemo E2E Automation Framework

Playwright (Java) + Cucumber BDD + Page Object Model + Maven + Jenkins.

Automates the following flow on https://www.saucedemo.com/:
1. Login (`standard_user` / `secret_sauce`) — shared **Background** step, reused by every scenario.
2. Add "Sauce Labs Backpack" to the cart.
3. Open the cart and verify the item is present.
4. Checkout: First Name = `Ram`, Last Name = `Chennale`, Zip = `123456`.
5. Click **Continue** → **Finish**.
6. Assert `"Thank you for your order!"` is displayed.

## Project Structure

```
swaglabs-playwright-cucumber-java/
├── pom.xml
├── Jenkinsfile
├── README.md
└── src/test/
    ├── java/
    │   ├── pages/                     # Page Object Model
    │   │   ├── LoginPage.java
    │   │   ├── InventoryPage.java
    │   │   ├── CartPage.java
    │   │   └── CheckoutPage.java
    │   ├── stepdefinitions/
    │   │   ├── Hooks.java             # @Before/@After — browser lifecycle, failure screenshots
    │   │   ├── LoginSteps.java
    │   │   └── CheckoutSteps.java
    │   ├── runners/
    │   │   └── TestRunner.java        # JUnit + Cucumber entry point (what Maven Surefire runs)
    │   └── utils/
    │       ├── PlaywrightFactory.java # Launches/tears down Playwright browser
    │       ├── TestContext.java       # Shared Page instance, injected via PicoContainer
    │       └── ConfigReader.java      # Reads config.properties (overridable via -D flags)
    └── resources/
        ├── config.properties
        └── features/
            └── swagLabsCheckout.feature
```

## Design notes

- **Page Object Model**: every page has its own class holding locators + actions. Step definitions never contain a raw selector.
- **Shared state across step classes**: Cucumber creates a fresh `TestContext` per scenario and injects the *same* instance into `Hooks`, `LoginSteps`, and `CheckoutSteps` constructors via `cucumber-picocontainer` — that's how the same Playwright `Page` is passed between "Login" steps and "Checkout" steps.
- **Background in the feature file**: the login steps run once per scenario automatically, so any new scenario added to this feature (or a new feature using the same `stepdefinitions` glue) gets a logged-in session for free.
- **Config-driven browser/headless**: `config.properties` holds defaults; Jenkins overrides them at runtime with `-Dbrowser=... -Dheadless=true` without touching code.
- **Failure diagnostics**: `Hooks.tearDown` attaches a screenshot to the Cucumber report automatically whenever a scenario fails.

## Prerequisites

- Java 11+
- Maven 3.6+
- Node is **not** required — Playwright's Java driver bundles its own browsers.

## Install & first-time browser download

```bash
mvn clean install -DskipTests
mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps"
```

## Run the suite locally

```bash
# Default (chromium, headed, as configured in config.properties)
mvn test

# Override browser / headless mode from the command line
mvn test -Dbrowser=firefox -Dheadless=true

# Run only a tag
mvn test -Dcucumber.filter.tags="@regression"
```

## Reports

After a run, reports are generated under `target/cucumber-reports/`:
- `cucumber-pretty.html` — human-readable HTML report
- `CucumberTestReport.json` — consumed by the Jenkins Cucumber Reports plugin
- `CucumberTestReport.xml` — JUnit-format XML, consumed by Jenkins' built-in `junit` step

## Jenkins Pipeline

The included `Jenkinsfile` is a declarative pipeline that:
1. Checks out the repo.
2. Runs `mvn clean install -DskipTests`.
3. Installs Playwright's browser binaries on the agent.
4. Runs the regression suite (`mvn test -Dbrowser=<param> -Dheadless=true`).
5. Publishes the Cucumber HTML report (requires the **Cucumber Reports** Jenkins plugin) and JUnit XML results.
6. Archives the report artifacts.

### Jenkins setup requirements
- Install plugins: **Cucumber Reports**, **JUnit**, **Pipeline**.
- Configure a Maven installation named `Maven3` and a JDK named `JDK11` under *Manage Jenkins → Global Tool Configuration* (or rename them in the `Jenkinsfile` to match what you already have).
- Update the `git url` in the `Checkout Code` stage to point at your actual repository.
- Trigger regression runs via a webhook, a poll SCM trigger, or manually with the `BROWSER` build parameter.

## Extending the framework

To add a new scenario that also needs a logged-in session, just add it under the same `Feature:` block — it will automatically inherit the `Background`. To test a different flow entirely (e.g. an unauthenticated flow), create a new `.feature` file with its own `Background` (or none), and add any new step definitions under `stepdefinitions/` following the same POM-first pattern.
