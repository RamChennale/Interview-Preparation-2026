# SauceDemo — Selenium WebDriver + Cucumber + TestNG Automation Framework

End-to-end regression automation framework for **https://www.saucedemo.com/**, built with:

- **Java 17**
- **Selenium WebDriver 4** — browser automation
- **Cucumber 7.x (Gherkin)** — BDD feature files
- **TestNG** — test runner/reporting (via `AbstractTestNGCucumberTests`)
- **Page Object Model (POM)** — one class per page, standard Selenium `PageFactory`/`@FindBy`
- **WebDriverManager** — auto-resolves browser driver binaries (no manual chromedriver download)
- **Maven** — build & dependency management
- **Jenkins (declarative pipeline)** — CI trigger for the regression suite

---

## 1. Project Structure

```
saucedemo-cucumber-testng/
├── pom.xml
├── testng.xml                              # TestNG suite -> drives the Cucumber runner
├── Jenkinsfile
├── src/test
│   ├── java/com/saucedemo
│   │   ├── pages/                          # Page Object Model
│   │   │   ├── BasePage.java               # shared explicit-wait helpers
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductsPage.java
│   │   │   ├── CartPage.java
│   │   │   └── CheckoutPage.java
│   │   ├── hooks/
│   │   │   └── Hooks.java                  # @Before/@After: WebDriver lifecycle, screenshots
│   │   ├── stepdefinitions/
│   │   │   └── CheckoutStepDefinitions.java
│   │   ├── runners/
│   │   │   └── TestRunner.java             # extends AbstractTestNGCucumberTests
│   │   └── utils/
│   │       ├── DriverManager.java          # ThreadLocal<WebDriver> + WebDriverManager setup
│   │       └── ConfigReader.java           # -D system property > config.properties
│   └── resources
│       ├── features/checkout.feature       # Gherkin: Background + Scenario
│       ├── config.properties               # default browser/base.url/headless
│       └── cucumber.properties             # default cucumber.filter.tags
└── target/surefire-reports, target/cucumber-reports   # generated reports
```

### Design pattern
- **Page Object Model (POM):** every screen (`LoginPage`, `ProductsPage`, `CartPage`, `CheckoutPage`) extends `BasePage`, which wires up `@FindBy` fields via Selenium's `PageFactory.initElements()` and provides `waitUntilVisible`/`waitUntilClickable` explicit-wait helpers. Locators never leak into step definitions.
- **Step Definitions** are a thin glue layer: they instantiate the next page object after a navigating action and call business-readable Page Object methods — no raw Selenium calls.
- **`DriverManager`** centralizes WebDriver creation behind a `ThreadLocal`, so the same codebase supports parallel TestNG execution safely if enabled later.

---

## 2. Feature File — Background for common login

`src/test/resources/features/checkout.feature` uses a Cucumber **`Background`** so every scenario in the file automatically logs in with `standard_user` / `secret_sauce` before executing its own steps:

```gherkin
Background: Common login step used by all scenarios in this feature
  Given I launch the SauceDemo application
  When I login with username "standard_user" and password "secret_sauce"
  Then I should be logged in successfully and land on the products page
```

The main regression scenario then:
1. Adds **Sauce Labs Backpack** to the cart
2. Verifies the cart badge shows `1`
3. Opens the cart and verifies the item is present
4. Clicks **Checkout**
5. Fills in First Name = `Ram`, Last Name = `Chennale`, Zip/Postal Code = `123456`
6. Clicks **Continue**, then **Finish**
7. Asserts the confirmation text **"Thank you for your order!"** is displayed

---

## 3. Prerequisites

| Tool           | Version  |
|----------------|----------|
| JDK            | 17+      |
| Maven          | 3.8+     |
| Chrome/Firefox | latest   |
| Jenkins        | 2.4xx+ (with Pipeline, HTML Publisher plugins) |

> WebDriverManager auto-downloads/caches the matching driver binary for the configured browser — no manual setup required.

---

## 4. Running Locally

```bash
# Run the full regression suite (default tag: @regression), headless Chrome
mvn clean test

# Run against Firefox, non-headless, filtering a specific tag
mvn clean test -Dbrowser=firefox -Dheadless=false -Dcucumber.filter.tags="@smoke"

# View reports after the run
open target/cucumber-reports/cucumber.html   # Cucumber living-documentation-style report
open target/surefire-reports/index.html      # TestNG report
```

Useful override properties (also exposed as Jenkins parameters):

| Property                 | Default        | Description                          |
|---------------------------|----------------|--------------------------------------|
| `browser`                 | `chrome`       | `chrome` or `firefox`                |
| `base.url`                | saucedemo URL  | Application under test               |
| `headless`                | `true`         | Run browser headless                 |
| `cucumber.filter.tags`    | `@regression`  | Cucumber tag expression              |

> **Why isn't `tags` set directly in `@CucumberOptions`?** Values in `@CucumberOptions` take precedence over system properties/CLI overrides. Hardcoding `tags` there would make `-Dcucumber.filter.tags` silently stop working. Instead, the default lives in `cucumber.properties`, letting the CLI/Jenkins parameter override it as expected.

---

## 5. Jenkins CI/CD

The included `Jenkinsfile` defines a **declarative pipeline**:

1. **Checkout** — pulls source from SCM
2. **Build** — `mvn clean compile test-compile`
3. **Run Regression Tests** — `mvn clean test` with parameterized browser/tags/headless mode
4. **Publish Reports** — publishes the Cucumber HTML report and the TestNG/Surefire report via the HTML Publisher plugin
5. **Archive Results** — archives JUnit-format XML (from the Cucumber `junit` plugin) + TestNG surefire reports

### Jenkins setup
1. Create a new **Pipeline** job (or Multibranch Pipeline) pointing at this repository, `Jenkinsfile` at the root.
2. Configure global tools with the exact names referenced in the `Jenkinsfile`: a JDK named `JDK_17`, a Maven install named `Maven_3.9`.
3. Install the **HTML Publisher** plugin (for the report tabs) and the **JUnit** plugin (bundled).
4. Trigger manually (**Build with Parameters**), on SCM webhook, or via the built-in nightly `cron('H 2 * * *')` schedule for regression runs.

---

## 6. Extending the Framework

- **New page:** add a class under `pages/`, extend `BasePage`, expose behavior-level methods.
- **New scenario:** add to `checkout.feature` (or a new `.feature` file) — the shared `Background` login is reused automatically for every scenario in that file.
- **New step defs:** add methods to `CheckoutStepDefinitions.java` or create a new step class; reference page objects the same way (instantiate with `DriverManager.getDriver()`).
- **Tag-based suites:** tag scenarios (`@smoke`, `@regression`, `@checkout`) and filter via `-Dcucumber.filter.tags`.
- **Parallel execution:** set `runScenariosInParallel()` to `true` in `TestRunner` and add `<parameter name="dataproviderthreadcount" value="4"/>` (or configure `data-provider-thread-count` in `testng.xml`) — `DriverManager`'s `ThreadLocal<WebDriver>` already supports this safely.
