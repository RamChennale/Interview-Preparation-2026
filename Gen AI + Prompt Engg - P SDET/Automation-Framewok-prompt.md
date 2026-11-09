Here's the crafted prompt, ready to feed to an AI (or a teammate) to generate the full automation framework:

---

## ROLE
You are a **Senior QA Automation Engineer**, with 10+ years of expertise in designing scalable **Selenium WebDriver + Java + Cucumber BDD** hybrid automation frameworks (Page Object Model), Maven build management, Extent Reports, and Log4j-based logging for enterprise web applications.

## CONTEXT
Our QA team maintains automation for **OrangeHRM Demo** (`https://opensource-demo.orangehrmlive.com/`, login: Admin/admin123) but is facing several known problems:
- Flaky test execution due to poor waits/synchronization
- Unclear or missing smoke test coverage
- Long regression cycles with no test tagging/parallelization strategy
- Weak reporting — failures aren't visible or traceable without screenshots/logs
- Inconsistent release confidence due to unreliable pass/fail signals
- Poor maintainability — locators and test data hardcoded inside test/step classes

We need a redesigned framework that fixes these issues while automating a **meaningful Admin/User Management end-to-end workflow** (Login → Search/Filter Users → Create/Update User → Validate → Verify Save/Update → Logout).

The framework must strictly follow this format:
- **Framework type**: Hybrid Framework with Page Object Model (POM) + Cucumber BDD
- **Tools**: Selenium WebDriver, Java, Cucumber BDD (Feature files + Step Definitions), Maven, Extent Reports, Log4j
- **Folder structure**:
  - `src/main/java` → Page classes, Utility classes, Base class
  - `src/test/java` → Step Definition classes, Runner classes, Hooks
  - `src/test/resources` → `.feature` files, `.properties` files for dynamic test data
- **Flow**:
  - `BaseClass` handles WebDriver init/teardown (`@Before`/`@After` Cucumber Hooks)
  - `PageObjects` contain locators (plain `By`, no PageFactory) + action/business methods
  - `StepDefinitions` call page object methods + hold assertions
  - Extent Reports for reporting; Log4j for logging; screenshots auto-attached on failure

## TASK
Design and generate the **complete automation framework** — folder/package structure, Maven `pom.xml` dependencies, `BaseClass`, Page Object classes, Cucumber `.feature` file(s), Step Definition classes, Runner class, `config.properties`/test data properties, Log4j config, Extent Report + screenshot-on-failure utility, and a tagging strategy (`@smoke`, `@regression`) — for the following workflow:

1. **Login** to OrangeHRM as Admin
2. **Navigate** to PIM/Admin → User Management → Users
3. **Search/Filter** an existing user by username/role/status
4. **Create a new system user** (or update an existing one) with valid test data
5. **Validate** success confirmation message/UI state after save
6. **Verify** the created/updated user appears correctly in the search/grid results (persistence check)
7. **Logout** and validate return to login page

Address the stated pain points explicitly: use explicit/fluent waits (no `Thread.sleep`) to fix flakiness, tag scenarios for smoke vs. regression to shorten cycles, externalize data to `.properties`/config files for maintainability, and wire Extent Reports + Log4j + failure screenshots for visibility.

## OUTPUT FORMAT
- **Format**: Structured technical document — headers + code blocks (Gherkin, Java, XML, properties)
- **Length**: Comprehensive — full working code artifacts, not pseudocode; no arbitrary word cap
- **Tone**: Technical, precise, professional (as for a QA design review/handover doc)
- **Structure** (in this order):
  1. Framework architecture overview (diagram/description of layers)
  2. Folder/package structure tree
  3. `pom.xml` with all required dependencies/plugins
  4. `config.properties` / `testdata.properties`
  5. `log4j2.properties` (or `.xml`) config
  6. `BaseClass` (driver factory, hooks, waits config)
  7. Page Object classes (`LoginPage`, `AdminUserPage` / relevant PIM pages) — locators + methods
  8. Cucumber `.feature` file(s) with `@smoke`/`@regression` tags and scenario outline (where applicable, e.g., data-driven user creation)
  9. Step Definition class(es) mapped to the feature
  10. `TestRunner` class (Cucumber options, tags, plugin for Extent/JSON report)
  11. Extent Report + screenshot-on-failure utility class
  12. Execution strategy notes: how this design resolves each stated pain point (flakiness, smoke coverage, regression time, reporting, release confidence, maintainability)

## CONSTRAINTS
- No `Thread.sleep()` anywhere — use `WebDriverWait`/`ExpectedConditions` only
- No PageFactory/`@FindBy` — use plain `By` locators as specified
- No hardcoded test data or credentials in step/page classes — pull from properties files
- Must include at least one `@smoke` and one `@regression` tagged scenario to demonstrate the coverage split
- Must include failure-handling logic (screenshot capture + attach to Extent Report) inside Cucumber Hooks, not scattered in step definitions
- Keep step definitions thin — business logic lives in Page Object methods only
- All code must be compilable/runnable Java (correct imports, no placeholders like `// TODO logic here`)
- Do not use TestNG annotations — this is a pure Cucumber BDD runner setup

## EXAMPLES (illustrative — not to be copied verbatim)
**Good Gherkin style expected:**
```gherkin
@regression
Scenario Outline: Admin creates a new system user
  Given Admin logs into OrangeHRM
  When Admin navigates to the Admin User Management page
  And Admin creates a new user with username "<username>", role "<role>" and status "<status>"
  Then the user should be created successfully
  And the new user should appear in the search results

  Examples:
    | username    | role  | status |
    | qa_auto_01  | ESS   | Enabled|
```
**Bad practice to avoid:** a step definition directly calling `driver.findElement(By.id(...))` instead of delegating to a Page Object method.

## SUCCESS CRITERIA
The output is correct if:
- A mid-level SDET could clone the structure and run `mvn test -Dcucumber.filter.tags="@smoke"` successfully without modification
- Every stated pain point (flakiness, smoke clarity, regression time, reporting, release confidence, maintainability) has a **directly traceable design decision** solving it
- The workflow is genuinely end-to-end (login → search → create/update → validate → verify persistence → logout), not a single isolated action
- Code compiles conceptually (correct Java/Cucumber/Maven syntax, valid dependency versions) and follows the exact folder/tooling structure specified

---

Want me to save this as a Markdown file, or go ahead and actually generate the full working framework code from this prompt right now?

## ROLE
You are a **Senior QA Automation Engineer**, design a simple **Selenium WebDriver + Java + Cucumber BDD** hybrid automation frameworks (Page Object Model), 
Maven build management, Extent Reports.

## CONTEXT
Our QA team maintains automation for **OrangeHRM Demo** (`https://opensource-demo.orangehrmlive.com/`, login: Admin/admin123) 


- ***Framework type***: Hybrid Framework Page Object Model (POM) with Cucumber BDD .
- **Tools used**: Selenium WebDriver, Java,  Maven, Extent Reports, 
- **Folder structure**: 
  - `src/main/java` → Page classes, Utility classes, Base class, DriverManager with ThreadLocal
  - `src/test/java` → Test classes
  - `src/test/resources` → properties file to read test data user name and password 
  
 Keep it simple only with below files 
 1. Feature file
 2. Correspoding StepDefinitions file 
 3. Page Class (Locators + Actions method which are called in StepDefination of the feature file)
 4. DriverManger 
 5. TestRunner Class
  