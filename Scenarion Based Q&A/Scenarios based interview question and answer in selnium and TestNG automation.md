Provid the list scenarios based interview question and answer in selnium and TestNG automation  for Senior test automation Engineer having 10 years experience in automation ?

# Scenario-Based Selenium + TestNG Interview Q&A (Senior Level, 10 Years)

## A. Framework Design and Architecture

**Q1. You join a team with a poorly maintained framework: 40% flaky tests, a 3-hour regression, and duplicated code. What do you do?**

I would not rewrite it immediately. I'd take these steps:
1. **Measure first.** Pull the last 20-30 runs from CI and classify failures as product bug, test bug, environment, data, or timing.
2. **Quarantine flaky tests** into a separate TestNG group (`@Test(groups="quarantine")`) so they don't block the pipeline, and track them on a fix list.
3. **Fix the root causes** in order of impact: replace `Thread.sleep` with explicit waits, isolate test data, and make tests independent.
4. **Refactor incrementally** into a layered design: tests → page objects/components → utilities → driver factory.
5. **Parallelize** once tests are independent.
6. **Set a metric**, e.g. flaky rate under 2% and regression under 45 minutes.

**Q2. How do you design a framework that supports multiple environments, browsers, and parallel execution?**

- **Config:** `config.properties` per environment (`qa`, `uat`, `stage`), selected with `-Denv=qa`, loaded by a `ConfigReader` singleton.
- **Driver:** A `DriverFactory` that returns a `WebDriver` based on browser/headless/remote parameters.
- **Thread safety:** Use `ThreadLocal<WebDriver>`.

```java
public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void init(String browser) {
        driver.set(DriverFactory.create(browser));
    }
    public static WebDriver get() { return driver.get(); }
    public static void quit() {
        if (driver.get() != null) { driver.get().quit(); driver.remove(); }
    }
}
```

- **Parameters:** Pass the browser from `testng.xml` using `@Parameters("browser")` in `@BeforeMethod`.
- **Parallelism:** `parallel="methods"` or `"classes"` with `thread-count`.

**Q3. Why ThreadLocal, and what goes wrong without it?**

Without it, a static `WebDriver` is shared across threads. Test A's click lands in Test B's browser, giving random failures, wrong screenshots, and `NoSuchSession` errors. `ThreadLocal` gives each thread its own driver. Always call `remove()` in teardown to avoid memory leaks in thread pools.

**Q4. Page Object Model: when does it become a problem, and how do you address it?**

POM breaks down when pages become huge "god classes" and shared widgets (headers, grids, date pickers) are duplicated. I'd:
- Create **component objects** (`HeaderComponent`, `DataGrid`, `DatePicker`) and compose them into pages.
- Keep **assertions out of page classes**; pages expose behavior and state, and tests assert.
- Use **fluent returns** (`loginPage.login(u,p)` returns `HomePage`).

## B. Synchronization and Flakiness

**Q5. A test passes locally but fails randomly in CI. How do you debug it?**

1. Check whether it fails at the same step. Review screenshot, DOM dump, browser console logs, and network logs.
2. Common CI differences: slower CPU, headless mode, different viewport (element hidden or off-screen), and parallel load.
3. Fixes:
   - Set a fixed window size (`--window-size=1920,1080`).
   - Replace implicit/static waits with explicit conditions.
   - Scroll into view or use `elementToBeClickable`.
   - Check for test data collisions from parallel runs.
4. Re-run 20-50 times in a loop on CI to confirm stability before closing.

**Q6. Implicit vs explicit vs fluent wait: what do you use and why?**

- **Implicit:** a global polling timeout for `findElement`. I avoid mixing it with explicit waits because it can lead to unpredictable total wait times.
- **Explicit (`WebDriverWait`)**: condition-based and my default.
- **Fluent:** explicit wait with custom polling and ignored exceptions, for slow or spinner-heavy screens.

```java
Wait<WebDriver> wait = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofMillis(500))
    .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

WebElement el = wait.until(d -> d.findElement(By.id("report")));
```

**Q7. How do you handle `StaleElementReferenceException`?**

It happens when the DOM re-renders after the element was located. I:
- Avoid storing `WebElement` as long-lived fields. Store `By` locators and locate on use.
- Wrap in a retry helper:

```java
public static WebElement retryFind(By by, int attempts) {
    for (int i = 0; i < attempts; i++) {
        try {
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
            return el;
        } catch (StaleElementReferenceException e) { /* retry */ }
    }
    throw new RuntimeException("Element not stable: " + by);
}
```

- Or wait on `ExpectedConditions.refreshed(...)`.

**Q8. `ElementClickInterceptedException`: the element is there but the click fails. What now?**

Usually an overlay, loader, sticky header, or cookie banner covers it. I'd:
1. Wait for the loader/overlay to become invisible (`invisibilityOfElementLocated`).
2. Scroll into view (center) using `scrollIntoView({block:'center'})`.
3. Dismiss the banner or modal.
4. Use JS click only as a last resort, because it bypasses real user behavior and can hide genuine UI bugs.

**Q9. A page loads data via AJAX with no visible loader. How do you wait reliably?**

- Wait for a **specific data-dependent element** (row count > 0, a text value).
- Or wait for `document.readyState == 'complete'` plus `jQuery.active == 0` (if jQuery is used).
- For SPAs, wait for a network-idle signal through a custom JS flag or the DevTools protocol, or prefer an API-level check.

## C. Locators and Tricky Elements

**Q10. Locators change frequently because of dynamic IDs. How do you make them stable?**

- Ask devs to add `data-testid` / `data-qa` attributes. This is the best long-term fix.
- Otherwise use relative XPath/CSS with stable anchors: `//label[text()='Email']/following-sibling::input`.
- Use `contains()` or `starts-with()` for partially dynamic values.
- Keep all locators centralized in page classes, never in tests.

**Q11. How do you handle iframes, shadow DOM, multiple windows, and alerts?**

```java
// iframe
driver.switchTo().frame("frameName");
driver.switchTo().defaultContent();

// shadow DOM (Selenium 4)
SearchContext shadow = driver.findElement(By.cssSelector("#host")).getShadowRoot();
shadow.findElement(By.cssSelector(".inner")).click();

// new window/tab
String parent = driver.getWindowHandle();
for (String h : driver.getWindowHandles())
    if (!h.equals(parent)) driver.switchTo().window(h);

// alert
driver.switchTo().alert().accept();
```

Selenium 4 also has `driver.switchTo().newWindow(WindowType.TAB)`.

**Q12. How do you handle a dynamic web table, such as validating a row in a paginated grid?**

Write a reusable grid component: find the header index by column name, loop through pages until the key value is found, and return the row's cell values. Build the XPath dynamically from the column header rather than hardcoding indexes, so column reordering doesn't break tests.

**Q13. File upload and download?**

- **Upload:** `sendKeys(absolutePath)` on `<input type="file">`. For custom widgets, I reveal the hidden input via JS.
- **Download:** Set the download directory through browser preferences, wait for the file to appear (and the `.crdownload` file to vanish) with a polling utility, then validate content with Apache POI or PDFBox.

## D. TestNG Scenarios

**Q14. How do you rerun only the failed tests automatically?**

Implement `IRetryAnalyzer` and attach it globally with `IAnnotationTransformer`, so you don't annotate every test:

```java
public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0; private static final int MAX = 1;
    public boolean retry(ITestResult r) { return count++ < MAX; }
}

public class RetryTransformer implements IAnnotationTransformer {
    public void transform(ITestAnnotation a, Class c, Constructor k, Method m) {
        a.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
```

Register it as a listener. I also report retried-then-passed tests as "flaky" so retries don't hide problems. Retry is a safety net, not a fix.

**Q15. Explain how you'd use listeners in a real framework.**

- `ITestListener`: screenshot on failure, log start/end, attach to the report.
- `ISuiteListener`: set up and tear down the report.
- `IInvokedMethodListener`: pre/post hooks around every method.
- `IAnnotationTransformer`: global retry or toggling tests.

Registered via `<listeners>` in `testng.xml` or `@Listeners`.

**Q16. Tests depend on each other (login → create order → verify). Is that good?**

Generally not. `dependsOnMethods` creates a chain where one failure skips everything, and it blocks parallelism. Better:
- Make each test independent by creating its data via **API or DB setup** in `@BeforeMethod`.
- Reserve `dependsOnMethods` / `dependsOnGroups` for a true E2E workflow test (e.g. a single smoke journey).

**Q17. How do you organize smoke, sanity, and regression suites?**

Use **groups**: `@Test(groups={"smoke","regression"})`, and select with `<groups><run><include name="smoke"/></run></groups>` or `-Dgroups=smoke` through Maven Surefire. Separate XML suites for each type, and the CI pipeline triggers the relevant one (smoke on every commit, full regression nightly).

**Q18. DataProvider scenarios: how do you handle large or external test data, and run it in parallel?**

```java
@DataProvider(name = "users", parallel = true)
public Object[][] users() { return ExcelUtil.read("users.xlsx", "Sheet1"); }

@Test(dataProvider = "users")
public void loginTest(String user, String pwd, String expected) { ... }
```

- Source from Excel/JSON/CSV or DB.
- `parallel = true` with `data-provider-thread-count` in the suite XML.
- Each iteration must use its own driver (ThreadLocal) and its own data (no shared accounts).

**Q19. Difference between `@BeforeSuite/@BeforeTest/@BeforeClass/@BeforeMethod`, and where do you launch the browser?**

Order: Suite → Test → Class → Method. For parallel runs I launch the browser in `@BeforeMethod` (a fresh session per test, isolated state) and quit in `@AfterMethod`. `@BeforeClass` is faster but shares state across tests in the class, which is riskier. Suite-level setup is for reports, config, and DB seeding.

**Q20. `parallel="tests"` vs `"classes"` vs `"methods"`: which do you choose?**

- **methods:** maximum speed, but demands fully independent tests and thread-safe pages.
- **classes:** safer. Methods inside a class run sequentially, which suits stateful classes.
- **tests:** parallel across `<test>` tags, useful for cross-browser runs (Chrome in one `<test>`, Firefox in another).

I start with `classes`, then move to `methods` as independence improves.

## E. CI/CD, Grid, and Reporting

**Q21. How is your suite integrated with CI?**

Maven + Surefire runs the suite (`mvn test -DsuiteXmlFile=smoke.xml -Denv=qa -Dbrowser=chrome`). Jenkins/GitHub Actions triggers it on commit, PR, and nightly schedules. Artifacts are archived (Extent/Allure report, screenshots, logs), and failures notify Slack/email. Quality gates fail the build below a pass-rate threshold.

**Q22. How do you scale execution with Selenium Grid or Docker?**

Selenium Grid 4 (Hub/Node or Standalone) with Docker containers (`selenium/standalone-chrome`) via Docker Compose or Kubernetes. Tests use `RemoteWebDriver` with a grid URL and capabilities/options. Benefits are elastic scaling, consistent environments, and cross-browser coverage. I'd watch session timeouts, video recording, and resource limits per node.

**Q23. What would you put in the failure report to help developers?**

Test name, step reached, screenshot, page URL, browser console errors, relevant network/API response, environment/build version, and a link to the logs/video. Allure or Extent with failure categorization (product defect vs test defect vs environment) makes triage fast.

## F. Strategy and Leadership-Level Scenarios

**Q24. When should a UI test NOT be automated with Selenium?**

When it can be verified at a lower layer. I follow the test pyramid: business logic and validations go to unit/API tests (RestAssured), and UI automation is reserved for critical user journeys and integration points. Also avoid automating highly unstable UI, one-time tests, and anything needing heavy visual judgment (use visual testing tools instead).

**Q25. How do you combine API and UI testing to speed up tests?**

Create data and authenticate through API, then open the UI at the exact page under test. For example, create a client via REST call, inject the session cookie into the browser, and test only the screen under test. This cuts a 3-minute UI setup to seconds and removes dependency on earlier UI tests.

**Q26. A release is tomorrow and 30 tests are failing. What do you do?**

1. Triage within the hour: group failures by root cause, since often 30 failures come from 2-3 causes (environment down, a login change, a locator change).
2. Separate real product defects (raise with severity) from test or environment issues.
3. Fix quick test-side issues, and rerun.
4. Communicate a clear status to stakeholders: what's verified, what's at risk, and what's manually covered.
5. Afterward, run a retrospective to prevent recurrence.

**Q27. How do you mentor juniors and maintain code quality?**

Code reviews with a checklist (no sleeps, no hardcoded data, clear naming, no assertions in pages), coding standards, SonarLint/Checkstyle in CI, pairing sessions, a framework README with a "how to add a test" guide, and shared utilities so people don't reinvent helpers.

**Q28. How do you decide between Selenium and Playwright for a new project?**

Selenium has a mature ecosystem, broad language and browser support, Grid, and existing team skills. Playwright offers built-in auto-waiting, tracing, parallelism, and network interception, which reduces flakiness and setup effort. For a greenfield web-only project I'd evaluate Playwright seriously. For an existing large Selenium investment, I'd migrate selectively or run a pilot rather than rewrite everything.

## Quick Tips for the Interview

- Answer with the **situation → approach → tool/code → result** structure.
- Use **numbers** from your own work, e.g. the regression-time reduction from your framework.
- Mention **trade-offs** (e.g. retry vs fixing root cause). Seniors are judged on judgment, not just syntax.
- Be ready to **write code live** for: ThreadLocal driver, a custom wait, a retry analyzer, a DataProvider, and a dynamic XPath.

If you'd like, I can also prepare a **Java coding round** set (string/array/collections problems commonly asked with Selenium roles) or **mock follow-up questions** on any of these scenarios.


================================================================================================================================================================
get follow up question and answer for ? with brief description how to explain it in interview 
write code live for: ThreadLocal driver, a custom wait, a retry analyzer, a DataProvider, and a dynamic XPath.

# Live Coding Round: Explanation Scripts and Follow-Up Q&A

For each topic: **say this first**, then **write the code**, then be ready for the **follow-ups**. Narrate while you type. Interviewers score your reasoning as much as the syntax.

---

## 1. ThreadLocal WebDriver

**How to explain (30 seconds):**
"WebDriver is not thread-safe. In parallel runs a static driver gets shared, so one test's click lands in another test's browser. I keep the driver in a `ThreadLocal`, so each thread gets its own session. I create it in `@BeforeMethod` and always call `remove()` in `@AfterMethod`."

```java
public class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void init(String browser) {
        WebDriver d;
        switch (browser.toLowerCase()) {
            case "firefox": d = new FirefoxDriver(); break;
            case "edge":    d = new EdgeDriver();    break;
            default:
                ChromeOptions o = new ChromeOptions();
                o.addArguments("--window-size=1920,1080");
                d = new ChromeDriver(o);
        }
        DRIVER.set(d);
    }

    public static WebDriver get() { return DRIVER.get(); }

    public static void quit() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();          // prevents leaks in pooled threads
        }
    }
}

public class BaseTest {
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.init(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() { DriverManager.quit(); }
}
```

**Follow-ups**

**Q: Why not just make the driver `synchronized` or keep it static?**
Synchronizing serializes the tests, so you lose parallelism. A static driver is still one shared session. `ThreadLocal` isolates state without locking.

**Q: Why call `remove()` after `quit()`?**
TestNG and the JVM reuse pool threads. Without `remove()`, the thread keeps a reference to a dead driver, which causes a memory leak and the risk of a stale `NoSuchSessionException` on reuse.

**Q: How does a listener take a screenshot on failure in parallel runs?**
The listener runs on the same thread as the test, so `DriverManager.get()` returns the right driver. I never pass the driver around or store it in a static field.

**Q: What if the test spawns its own thread?**
`ThreadLocal` values are not visible to child threads. `InheritableThreadLocal` can pass the value down, but I'd rather pass the driver explicitly to avoid hidden coupling.

**Q: Does `ThreadLocal` work with `parallel=true` DataProviders?**
Yes. Each data iteration runs on its own thread, and each calls `@BeforeMethod` and gets its own driver.

**Q: Where would you launch the driver for a remote Grid?**
Same place, but `init()` builds a `RemoteWebDriver` with the Grid URL and options from config.

---

## 2. Custom Wait

**How to explain:**
"I default to explicit waits, never `Thread.sleep`, and I don't mix them with implicit waits. For app-specific conditions, like a spinner disappearing or a table loading, I write a reusable custom condition on top of `FluentWait` with polling and ignored exceptions."

```java
public class WaitUtil {

    // Generic reusable wait
    public static <T> T waitFor(Function<WebDriver, T> condition,
                                int timeoutSec, int pollMs, String message) {
        return new FluentWait<>(DriverManager.get())
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofMillis(pollMs))
                .ignoring(NoSuchElementException.class,
                          StaleElementReferenceException.class)
                .withMessage(message)
                .until(condition);
    }

    // Custom condition: element has non-empty text
    public static String waitForText(By locator) {
        return waitFor(d -> {
            String t = d.findElement(locator).getText().trim();
            return t.isEmpty() ? null : t;       // null = keep polling
        }, 20, 500, "Text not loaded for " + locator);
    }

    // Custom condition: spinner gone, then element clickable
    public static WebElement clickableAfterLoader(By loader, By target) {
        waitFor(ExpectedConditions.invisibilityOfElementLocated(loader),
                30, 500, "Loader still visible");
        return waitFor(ExpectedConditions.elementToBeClickable(target),
                15, 500, "Not clickable: " + target);
    }
}
```

**Follow-ups**

**Q: Why not mix implicit and explicit waits?**
The timeouts can compound unpredictably, and the behavior differs across drivers. I set implicit wait to 0 and rely on explicit waits.

**Q: What makes a condition keep polling?**
Returning `null` or `false`, or throwing an exception that is in the ignored list. Anything else ends the wait.

**Q: `WebDriverWait` vs `FluentWait`?**
`WebDriverWait` extends `FluentWait` with defaults: 500 ms polling and `NotFoundException` ignored. Use `FluentWait` when you need a custom polling interval, extra ignored exceptions, or a custom message.

**Q: What happens on timeout?**
`TimeoutException` with your message, which is why I always add a descriptive one. It makes CI failures easy to triage.

**Q: How would you wait for an AJAX call with no spinner?**
Wait on a data-dependent condition (row count > 0, a value changed). If jQuery is used, also check `jQuery.active == 0` through `JavascriptExecutor`.

**Q: How do you avoid `StaleElementReferenceException` in waits?**
Ignore it in the wait so it re-locates on the next poll, and locate by `By` inside the lambda instead of holding a `WebElement`.

---

## 3. Retry Analyzer

**How to explain:**
"A retry analyzer reruns a failed test a limited number of times. I attach it globally with `IAnnotationTransformer`, so I don't touch every test. I treat it as a safety net, not a fix. Tests that pass on retry get reported as flaky so we fix the root cause."

```java
public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY = 1;
    private int attempt = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < MAX_RETRY) {
            attempt++;
            return true;               // rerun
        }
        return false;
    }
}

public class RetryTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
```

```xml
<listeners>
  <listener class-name="listeners.RetryTransformer"/>
</listeners>
```

**Follow-ups**

**Q: Does retry hide real bugs?**
It can, which is why I keep `MAX_RETRY` at 1, log every retry, and report "passed on retry" separately. A rising flaky count triggers a root-cause fix.

**Q: How do you retry only for specific failures?**
Inspect `result.getThrowable()` inside `retry()` and retry only for infrastructure-type exceptions (`TimeoutException`, `WebDriverException`), not for assertion failures.

```java
Throwable t = result.getThrowable();
return attempt < MAX_RETRY && !(t instanceof AssertionError) && ++attempt > 0;
```

**Q: How does a retried test appear in reports?**
TestNG marks the failed attempt as SKIPPED, then the final result is PASSED or FAILED. I filter retried-skips in the listener/report so the counts are not misleading.

**Q: Is the analyzer thread-safe with parallel runs and DataProviders?**
The counter is per analyzer instance, so for DataProvider iterations I key the count by method name plus parameters in a `ConcurrentHashMap`, or I confirm each iteration gets its own instance.

**Q: Alternative to the annotation transformer?**
`@Test(retryAnalyzer = RetryAnalyzer.class)` per test, or `-rerun failed` using `testng-failed.xml` after the run.

**Q: Do `@BeforeMethod`/`@AfterMethod` run again on retry?**
Yes, so the retried test starts with a clean browser session.

---

## 4. DataProvider

**How to explain:**
"A DataProvider runs one test with many data sets. I keep the data external (Excel, JSON, CSV), and make it parallel when each iteration is independent. With parallel on, every iteration needs its own driver and its own data, so no shared accounts."

```java
public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData", parallel = true)
    public Object[][] loginData() {
        return new Object[][] {
            {"admin",  "Admin@123", true},
            {"admin",  "wrong",     false},
            {"",       "",          false}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String user, String pwd, boolean shouldPass) {
        LoginPage page = new LoginPage(DriverManager.get());
        page.login(user, pwd);
        Assert.assertEquals(page.isLoggedIn(), shouldPass,
                "Login result mismatch for user: " + user);
    }
}
```

External-class provider, with the method name passed in:

```java
public class DataSource {
    @DataProvider(name = "fromJson")
    public static Iterator<Object[]> fromJson(Method m) throws IOException {
        List<Object[]> rows = new ArrayList<>();
        // read JSON file named after the test method: m.getName() + ".json"
        // rows.add(new Object[]{pojo});
        return rows.iterator();
    }
}

@Test(dataProvider = "fromJson", dataProviderClass = DataSource.class)
public void createClient(ClientData data) { ... }
```

**Follow-ups**

**Q: How do you control the number of parallel threads for a DataProvider?**
In the suite XML: `<suite data-provider-thread-count="5">`. The default is 10.

**Q: `Object[][]` vs `Iterator<Object[]>`?**
`Iterator` loads lazily, which suits large data sets and avoids holding everything in memory.

**Q: DataProvider vs `@Parameters` vs `@Factory`?**
- `@Parameters`: a few simple values from `testng.xml`.
- DataProvider: many data sets for one test.
- `@Factory`: creates multiple test class instances, each with its own state.

**Q: How do you run only certain rows?**
Use `indices = {0, 2}` on `@DataProvider`, or filter in the provider by a tag or column.

**Q: A DataProvider with a failing row: does it stop the rest?**
No. Each row is an independent test invocation, so the others still run.

**Q: How do you pass complex data?**
Map JSON/Excel rows to a POJO and pass `new Object[]{pojo}`, so the test signature stays clean.

**Q: Why must a DataProvider in another class be static?**
TestNG cannot instantiate the provider's class for you unless the provider is static (or the class has a no-arg constructor).

---

## 5. Dynamic XPath

**How to explain:**
"I avoid absolute XPath and index-based paths because they break on small layout changes. I anchor on something stable, like a label, header text, or `data-testid`, and navigate with axes (`following-sibling`, `ancestor`). For repeated patterns, I parameterize the XPath with `String.format`."

```java
public class DynamicLocators {

    // Input next to a label
    public static By inputByLabel(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']"
                      + "/following-sibling::input");
    }

    // Button by visible text (handles extra whitespace)
    public static By buttonByText(String text) {
        return By.xpath("//button[normalize-space(.)='" + text + "']");
    }

    // Partially dynamic id
    public static By startsWithId(String prefix) {
        return By.xpath("//*[starts-with(@id,'" + prefix + "')]");
    }

    // Table cell: row containing a key, column located by header name
    public static By cellByRowAndHeader(String rowKey, String header) {
        return By.xpath(
            "//tr[td[normalize-space()='" + rowKey + "']]"
          + "/td[count(//th[normalize-space()='" + header + "']"
          + "/preceding-sibling::th)+1]");
    }

    // Action link inside the row of a given item
    public static By rowAction(String rowKey, String action) {
        return By.xpath("//tr[td[text()='" + rowKey + "']]"
                      + "//a[normalize-space()='" + action + "']");
    }
}
```

Usage: `driver.findElement(cellByRowAndHeader("ABC Corp", "Status")).getText();`

**Follow-ups**

**Q: Why avoid `/html/body/div[3]/div[2]`?**
It is tied to the exact DOM structure, so any layout change breaks it. Relative XPath anchored on stable attributes survives UI changes.

**Q: `contains()` vs `starts-with()` vs `normalize-space()`?**
- `contains`: partial match anywhere.
- `starts-with`: stable prefix with a dynamic suffix, and more precise than `contains`.
- `normalize-space`: trims and collapses whitespace, which fixes text matches broken by line breaks or extra spaces.

**Q: XPath or CSS selector?**
CSS is faster and more readable for simple cases. XPath is needed for text matching, parent/ancestor navigation, and sibling axes. My order is `id` / `data-testid`, then CSS, then XPath.

**Q: How do you find the parent of an element?**
`//span[text()='Total']/parent::div` or `/..`; for higher levels, `ancestor::tr`.

**Q: How do you handle text with special quotes (`O'Brien`)?**
Use `concat()` in XPath, or wrap the value in double quotes inside the XPath. I also put this in a utility method so the escaping lives in one place.

**Q: Does XPath work inside shadow DOM?**
No. Use `getShadowRoot()` with CSS selectors.

**Q: Where do you keep locators?**
In page classes only, never in test classes, and parameterized locators as small helper methods.

---

## How to Handle the Live Coding Round

- **Say the problem first, then the approach, then code.** Interviewers want to hear the reasoning.
- **Name the trade-off in one sentence** (retry vs root cause, `contains` vs `starts-with`, implicit vs explicit).
- **Tie it to your own work.** For example: "In our broker/dealer framework we ran in parallel with ThreadLocal drivers, which was one of the pieces that cut regression time by about 70%."
- **If you blank on syntax, say so and write the structure.** Seniors are judged on design more than on remembering imports.

I can also do a **mock interview** where I ask these questions one at a time and you answer, then I review your answers.