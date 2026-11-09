# SauceDemo — Serenity BDD + Cucumber + Selenium Automation Framework

End-to-end regression automation framework for **https://www.saucedemo.com/**, built with:

- **Java 11**
- **Selenium WebDriver 4** — browser automation
- **Serenity BDD** — reporting, WebDriver lifecycle management, `@Steps`/`PageObject` support
- **Cucumber (Gherkin)** — BDD feature files
- **Page Object Model (POM)** — one class per page, no locators in step definitions
- **Maven** — build & dependency management
- **Jenkins (declarative pipeline)** — CI trigger for the regression suite

---

## 1. Project Structure

```
saucedemo-serenity-bdd/
├── pom.xml
├── serenity.conf
├── Jenkinsfile
├── src
│   └── test
│       ├── java/com/saucedemo
│       │   ├── pages/                     # Page Object Model
│       │   │   ├── LoginPage.java
│       │   │   ├── ProductsPage.java
│       │   │   ├── CartPage.java
│       │   │   └── CheckoutPage.java
│       │   ├── stepdefinitions/
│       │   │   └── CheckoutStepDefinitions.java
│       │   └── runners/
│       │       └── TestRunner.java        # @RunWith(CucumberWithSerenity.class)
│       └── resources
│           ├── features/
│           │   └── checkout.feature       # Gherkin scenarios (Background + Scenario)
│           └── logback.xml
└── target/site/serenity/index.html        # Generated living-documentation report
```

### Design pattern
- **Page Object Model (POM):** every screen (`LoginPage`, `ProductsPage`, `CartPage`, `CheckoutPage`) extends Serenity's `PageObject` and exposes only business-readable methods (`loginAs()`, `addProductToCart()`, `clickCheckoutButton()`…). Locators (`@FindBy`) never leak into step definitions.
- **Step Definitions** are a thin glue layer: they call Page Object methods and perform assertions — no Selenium calls directly.
- **`@Steps`** (Serenity) injects Page Objects into step definitions and drives the shared, Serenity-managed `WebDriver` instance — no manual driver setup/teardown needed.

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

| Tool          | Version  |
|---------------|----------|
| JDK           | 11+      |
| Maven         | 3.8+     |
| Chrome/Firefox| latest   |
| Jenkins       | 2.4xx+ (with Pipeline, HTML Publisher plugins) |

> Selenium Manager (bundled with Selenium 4.6+) auto-resolves the correct browser driver — no manual chromedriver download required.

---

## 4. Running Locally

```bash
# Run the full regression suite (default tag: @regression), headless Chrome
mvn clean verify

# Run against Firefox, non-headless, filtering a specific tag
mvn clean verify -Dwebdriver.driver=firefox -Dheadless.mode=false -Dcucumber.filter.tags="@smoke"

# Open the Serenity living-documentation report after the run
open target/site/serenity/index.html   # macOS
# or just browse to target/site/serenity/index.html
```

Useful override properties (also exposed as Jenkins parameters):

| Property                 | Default        | Description                          |
|---------------------------|----------------|--------------------------------------|
| `webdriver.driver`        | `chrome`       | `chrome` or `firefox`                |
| `webdriver.base.url`      | saucedemo URL  | Application under test               |
| `headless.mode`           | `true`         | Run browser headless                 |
| `cucumber.filter.tags`    | `@regression`  | Cucumber tag expression              |

---

## 5. Jenkins CI/CD

The included `Jenkinsfile` defines a **declarative pipeline**:

1. **Checkout** — pulls source from SCM
2. **Build** — `mvn clean compile`
3. **Run Regression Tests** — `mvn clean verify` with parameterized browser/tags/headless mode
4. **Publish Serenity Report** — publishes `target/site/serenity/index.html` via the HTML Publisher plugin
5. **Archive Results** — archives JUnit XML + Serenity + Cucumber JSON/HTML reports

### Jenkins setup
1. Create a new **Pipeline** job (or Multibranch Pipeline) pointing at this repository, `Jenkinsfile` at the root.
2. Configure global tools with the exact names referenced in the `Jenkinsfile`: a JDK named `JDK_11`, a Maven install named `Maven_3.9`.
3. Install the **HTML Publisher** plugin (for the Serenity report tab) and the **JUnit** plugin (bundled).
4. Trigger manually (**Build with Parameters**), on SCM webhook, or via the built-in nightly `cron('H 2 * * *')` schedule for regression runs.

---

## 6. Extending the Framework

- **New page:** add a class under `pages/`, extend `PageObject`, expose behavior-level methods.
- **New scenario:** add to `checkout.feature` (or a new `.feature` file) — the shared `Background` login is reused automatically for every scenario in that file.
- **New step defs:** add methods to `CheckoutStepDefinitions.java` or create a new step class; Serenity auto-wires `@Steps` Page Objects.
- **Tag-based suites:** tag scenarios (`@smoke`, `@regression`, `@checkout`) and filter via `-Dcucumber.filter.tags`.
