I've built the Cucumber + TestNG project on your same POM structure and zipped it as `pom-bdd-framework.zip`. I couldn't run it here because the sandbox has no network to download Maven dependencies. Run `mvn clean test` on your machine. If a menu locator fails, check it against the live site first, because I haven't tested them against OrangeHRM.

```
src/test/resources/features/orangehrm_navigation.feature
src/test/java/com/bdd/pom/
   base/            BasePage, DriverManager        (same as yours, _1 removed)
   pages/           LoginPage, DashboardPage       (By locators only)
   stepdefinitions/ Hooks, LoginSteps, DashboardSteps
   runners/         TestRunner                     (extends AbstractTestNGCucumberTests)
```

The feature file logs in once in a Background, then runs the scenarios:

```gherkin
Background:
  Given I am on the OrangeHRM login page
  When I login with username "Admin" and password "admin123"
  Then I should see the dashboard

Scenario: Dashboard header is shown after login
  Then the page header should be "Dashboard"

Scenario Outline: Open a module from the left menu
  When I click the "<menu>" menu
  Then the page header should be "<menu>"
  Examples: Admin | PIM | Leave
```

The flow is feature file → step definitions → page classes → `BasePage` waits → WebDriver. `Hooks` replaces `TestBase`: its `@Before` and `@After` create and quit the browser through the same `ThreadLocal` `DriverManager`. It also attaches a screenshot to the report when a scenario fails. Steps get the driver from `DriverManager.getDriver()`, which also fixes the shared instance-field problem I flagged earlier. `DashboardPage.menuItem(name)` is a parameterized `By` locator, so one method handles every menu item. This is the case where `@FindBy` would not work.

A Cucumber Background runs before every scenario, not once per feature, and each scenario gets a fresh browser. So "login once" means you write the login steps once, and Cucumber repeats them for each scenario, so the Outline logs in three times. Reusing a single login across the whole feature would need shared browser state, which makes tests depend on each other, so I'd avoid it. Be ready to explain this in an interview.

The HTML report is written to `target/cucumber-report.html`.