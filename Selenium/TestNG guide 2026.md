 TestNG — Core & Advanced

### Q11. Explain the TestNG annotation execution order with a real suite.
```
@BeforeSuite → @BeforeTest → @BeforeClass → @BeforeMethod
→ @Test
→ @AfterMethod → @AfterClass → @AfterTest → @AfterSuite
```
**Real use:** 
	`@BeforeSuite` — start Grid hub check/DB connection pool. 
	`@BeforeClass` — login once per class (reused across tests in read-only modules). 
	`@BeforeMethod` — fresh driver instance per test for isolation.

---

### Q12. How do you implement retry-on-failure for flaky tests without masking real bugs?
```java
public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY) {
            retryCount++;
            return true;
        }
        return false;
    }
}
```
Applied globally via a **listener**, so nobody has to annotate every `@Test(retryAnalyzer = ...)` manually:
```java
public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
```
**Real guardrail:** Retry count is capped at 2, and retried tests are flagged distinctly (not silently green) in
 Extent Reports so genuinely flaky tests get tracked and fixed, not just hidden — this distinction is what separates a senior answer from a junior one.

---

### Q13. How do you implement custom listeners for screenshots on failure + reporting?
```java
public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = DriverManager.getDriver();
        String screenshotPath = ScreenshotUtils.capture(driver, result.getName());
        ExtentReportManager.getTest().fail(result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().pass("Test passed");
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.initReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
    }
}
```
Registered in `testng.xml`:
```xml
<listeners>
    <listener class-name="listeners.TestListener"/>
    <listener class-name="listeners.AnnotationTransformer"/>
</listeners>
```
**Real use:** This is the single most commonly asked "show me framework code" question in senior interviews — auto screenshot-on-failure + Extent Report is near-universal 
in real frameworks.

---

### Q14. How do you run tests in parallel — method/class/test level — and what breaks when you do?
```xml
<suite name="RegressionSuite" parallel="methods" thread-count="10">
    <test name="ChromeTests">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="tests.LoginTest"/>
            <class name="tests.CheckoutTest"/>
        </classes>
    </test>
</suite>
```
**What breaks (real experience talking points):**
- Shared static WebDriver → fixed via ThreadLocal (Q9).
- Shared mutable test data / static counters → must be made thread-local or immutable.
- Extent Reports needs `ExtentTest` also wrapped in `ThreadLocal<ExtentTest>` or logs interleave incorrectly across threads.
- DB test data collisions when two parallel tests create/modify the same record → solved with unique data per thread (e.g., timestamp-suffixed usernames) or test-level DB transactions with rollback.

---

### Q15. Difference between `@Test(priority=)`, `dependsOnMethods`, and `dependsOnGroups`. When do you actually use dependency chains (and when do you avoid them)?
**Answer:** `priority` only controls execution *order* within independent tests. `dependsOnMethods`/`dependsOnGroups` make a test's execution conditional on another's success — if the dependency fails, dependents are marked **SKIPPED**, not failed.

```java
@Test(priority = 1)
public void loginTest() { ... }

@Test(dependsOnMethods = "loginTest", alwaysRun = false)
public void addToCartTest() { ... }
```
**Real senior-level nuance:** Dependencies are avoided in regression suites (tests should be independent/idempotent so failures are isolated and parallelizable), but used deliberately in **sequential business-flow smoke tests** (e.g., Login → Search → Add to Cart → Checkout → Order Confirmation) where it genuinely doesn't make sense to check "checkout" if "login" itself is broken — saves noisy failures in the report.

---

### Q16. How do you organize test suites for smoke/regression/sanity using TestNG groups?
```java
@Test(groups = {"smoke", "regression"})
public void testValidLogin() { ... }

@Test(groups = {"regression"})
public void testPasswordResetFlow() { ... }
```
```xml
<groups>
    <run>
        <include name="smoke"/>
    </run>
</groups>
```
**Real use:** 
CI pipeline runs `smoke` group on every PR (5 min feedback), 
full `regression` group nightly, and `sanity` group post-deployment to production — this staged strategy is a very common real answer.

---

