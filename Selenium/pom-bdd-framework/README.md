# POM + TestNG + Cucumber BDD (no PageFactory)

Run: `mvn clean test`
Report: `target/cucumber-report.html`

Flow: Feature file -> Step definitions -> Page classes (By locators) -> BasePage (waits) -> WebDriver
Lifecycle: Hooks (@Before / @After) create and quit the browser through DriverManager (ThreadLocal).
