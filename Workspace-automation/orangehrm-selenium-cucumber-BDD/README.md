# OrangeHRM Selenium + Cucumber BDD Automation

End-to-end UI automation for the **OrangeHRM demo** application, covering Login and the
**Admin > User Management** workflow.

- **Application under test:** https://opensource-demo.orangehrmlive.com/
- **Credentials (demo):** `Admin` / `admin123`
- **Stack:** Java 11, Selenium WebDriver 4.21, Cucumber 7.18 (JUnit 4 runner), PicoContainer (dependency injection),
  Maven, Extent Reports (Cucumber 7 adapter), WebDriverManager, JavaFaker

---

## 1. Execution steps

### 1.1 Prerequisites
| Requirement | Notes |
|---|---|
| JDK 11 or newer | JDK 21 also works (compiler target is 11) |
| Maven 3.6+ | `mvn -v` to check |
| Google Chrome | Installed locally; the driver is downloaded automatically (WebDriverManager) |
| Internet access | To reach the demo site and download Maven/driver dependencies |

### 1.2 Get the project
1. Unzip the archive. The zip contains a nested folder; work in the **inner** folder that holds `pom.xml`.
2. Open a terminal **in that folder** (the project root).

> **Important:** run everything from the project root. `ConfigReader` loads
> `src/test/resources/config.properties` through a relative path, so starting the tests from any other
> working directory fails with "Unable to load config file".

### 1.3 Configure (optional)
`src/test/resources/config.properties`

```properties
url=https://opensource-demo.orangehrmlive.com/
username=Admin
password=admin123
```

### 1.4 Run the tests
```bash
mvn clean test
```
This runs `runners/TestRunner`, which executes every scenario tagged `@Regression` (all 5 scenarios).

**Run a subset of scenarios:** change the `tags` value in `TestRunner.java` (for example `"@smoke"`,
`"@Login"`, `"@InvalidLogin"`, `"@createUser"`, `"@searchUser"`), or try overriding it from the command line:
```bash
mvn clean test -Dcucumber.filter.tags="@smoke"
```

### 1.5 Run from IntelliJ IDEA
1. Right-click the `pom.xml` > Maven > Reload project.
2. Make sure `src/test/java` is marked as **Test Sources Root**.
3. Right-click `src/test/java/runners/TestRunner.java` > Run.

Run through `TestRunner` (or `mvn test`), not by right-clicking a `.feature` file: the glue packages
(`stepdefinitions`, `utils`) are declared in `TestRunner`, so a direct feature run reports "Step undefined".

### 1.6 View the report
Open in a browser:
```
reports-output/ExtentReport/TestReport.html
```
It lists features, scenarios, steps, pass/fail status and tag categories. A previous run included in the
project shows **5 of 5 scenarios passing** (about 67 seconds in total).

### 1.7 What gets executed

| Feature | Scenario | Tags | Checks |
|---|---|---|---|
| Login | Successful login with valid credentials | `@Login` | Dashboard header is displayed |
| Login | Unsuccessful login with invalid credentials | `@InvalidLogin` | "Invalid credentials" error is shown |
| Login | Login and logout | `@Logout` | Dashboard displayed, then logout is performed |
| Admin - User Management | Admin creates a new system user | `@smoke @createUser` | Role ESS, employee, status Enabled, generated username/password; success toast after Save |
| Admin - User Management | Admin searches for an existing user | `@searchUser` | Searching `Admin` returns a row containing "Admin" |

All scenarios carry `@Regression` at feature level. `AdminUser.feature` logs in through a `Background`.

### 1.8 Project structure
```
src/main/java/pages/AdminUserPage.java     Admin > Users page object (add user, search)
src/main/java/pages/LoginPage.java         Login / logout page object
src/main/java/utils/DriverManager.java     ThreadLocal Chrome driver lifecycle
src/main/java/utils/ConfigReader.java      Reads config.properties
src/main/java/utils/Hooks.java             @Before / @After: start/stop browser, report entry
src/main/java/utils/TestContext.java       Shares the driver between step classes (PicoContainer)
src/main/java/utils/ExtentManager.java     Extent report helper
src/test/java/stepdefinitions/             LoginSteps, AdminUserSteps
src/test/java/runners/TestRunner.java      Cucumber runner (tags, plugins)
src/test/resources/features/               Login.feature, AdminUser.feature
src/test/resources/config.properties       URL and credentials
src/test/resources/extent.properties       Extent Spark report settings
```

---

## 2. Assumptions and limitations

### Assumptions
- The OrangeHRM demo site is reachable, its credentials are unchanged, and the UI layout matches the locators used
  (OrangeHRM 5 style `oxd-*` components).
- Chrome is installed and WebDriverManager can download a matching driver (internet access).
- The employee **"A8DCo 4Ys 010Z"** exists on the demo site. The create-user scenario selects that exact employee.
- The role option `ESS` and the status `Enabled` are available in the dropdowns.
- Tests run one at a time, on one machine, in a visible (non-headless) Chrome window.

**Stability**
- The hardcoded employee name depends on demo-site data, which is shared and resets periodically, so the create-user
  scenario can fail without any product defect.
- The search assertion waits for "some row" and then scans the rows. The table already lists all users (including
  `Admin`) before the filter is applied, so it can pass on stale data.
- Created users are not deleted, so test data accumulates on the demo site.

**Execution and reporting**
- Sequential execution. The driver is thread-safe, but the JUnit 4 Cucumber runner does not run scenarios in parallel.
- `@smoke` appears on one scenario and the runner is fixed to `@Regression`, so smoke and regression are not
  separable without editing the runner.
- Results depend on demo-site speed and availability; explicit waits are 15 seconds.

---

## 3. Summary of the QA / automation approach

**Goal.** Give the team a fast, readable check that the most business-critical admin path works: a user can sign in,
an admin can create and find a system user, and the session can be closed.

**Approach.**
- **BDD with Cucumber** keeps scenarios in plain business language so testers, developers and product owners can read
  and review the same artifact. Each scenario has one clear outcome.
- **Page Object Model** isolates locators and UI actions in `pages/`, so a layout change is fixed in one place and step
  definitions stay thin.
- **Dependency injection (PicoContainer)** shares the browser between step classes and hooks without static state;
  the driver itself is `ThreadLocal`, which prepares the framework for parallel runs.
- **Explicit waits only** (no fixed sleeps) handle asynchronous UI behaviour such as toasts and autocomplete.
- **Configuration-driven:** URL and credentials live in `config.properties`; unique usernames and passwords are
  generated with JavaFaker so repeated runs do not collide.
- **Tags** (`@Regression`, `@smoke`, `@Login`, ...) provide the hook for suite selection.
- **Reporting:** an Extent HTML report gives stakeholders pass/fail visibility per feature, scenario and step.

**Risk-based scope.** Login is the gate to everything else, so it gets positive and negative coverage. User creation
is the core Admin operation and is the smoke candidate. Searching confirms that user data can be retrieved.

**Current state and next step.** The framework is a working foundation: the bundled run shows all five scenarios
passing. The main gaps are the ones listed above (end-to-end chaining with post-save verification, removal of
demo-data dependencies, smoke/regression separation, failure evidence, parallel and CI execution). Closing items 1
to 5 of the improvement list would turn it from a demonstration into a dependable release-confidence suite.