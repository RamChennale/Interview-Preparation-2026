##DataProvider functions and features

Good call to ask for the full picture. My earlier "name, parallel and indices" was incomplete, since newer TestNG 7.x versions added more attributes. 
Here is the complete list. Check the exact version cutoffs against your TestNG version in the docs.

## 1. `@DataProvider` attributes

| Attribute | Default | What it does |
|---|---|---|
| `name` | method name | Name the test uses to find the provider |
| `parallel` | `false` | Runs the rows concurrently. Thread count is set at suite level (see section 5) |
| `indices` | all rows | Runs only the listed rows, e.g. `indices = {0, 2}` |
| `propagateFailureAsTestFailure` | `false` | If the provider throws, report it as a test failure instead of silently skipping the tests (newer 7.x) |
| `cacheDataForTestRetries` | `false` | Reuses the same data when a test is retried, instead of calling the provider again (newer 7.x) |

There is no `mode` attribute, which is why IntelliJ flagged it.

## 2. Return types a provider can have

| Return type | Notes |
|---|---|
| `Object[][]` | Standard: each inner array is one test run |
| `Iterator<Object[]>` | Lazy loading, so rows are created one at a time. Good for big or file/DB-driven data |
| `Object[]` / `Iterator<Object>` | Single-parameter tests only (newer versions) |

## 3. Parameters the provider method itself can accept

TestNG injects these automatically, with no setup needed:

| Parameter | Use |
|---|---|
| `java.lang.reflect.Method` | Give different data per test method |
| `ITestContext` | Access suite/test config, including `testng.xml` parameters |
| `ITestNGMethod` / `XmlTest` | Method metadata and `<test>` level parameters (newer versions) |

```java
@DataProvider(name = "browsers")
public Object[][] browsers(Method m, ITestContext ctx) {
    // per-method data
    if (m.getName().equals("smokeTest")) return new Object[][] { {"chrome"} };

    // read from testng.xml: <parameter name="headless" value="true"/>
    String headless = ctx.getCurrentXmlTest().getParameter("headless");
    return new Object[][] { {"chrome"}, {"firefox"} };
}
```

## 4. Features on the test side

| Feature | Example |
|---|---|
| Provider in another class | `@Test(dataProvider="browsers", dataProviderClass=TestData.class)`. The provider must be `static` |
| Extra injected args beside data | `public void t(String browser, Method m, ITestContext ctx)` |
| Used with `@Factory` | `@Factory(dataProvider="x")` creates a whole class instance per row |
| Provider inherited from a parent class | Works if the provider is public in the superclass |
| Multiple tests sharing one provider | Each test gets the full data set |
| Parameter matching | The row's length, order and types must match the test signature (autoboxing works, e.g. `true` to `boolean`), otherwise TestNG throws an error |

## 5. Parallel execution

```java
@DataProvider(name = "browsers", parallel = true)
```
Default thread count is 10. Change it in `testng.xml`:
```xml
<suite name="Suite" data-provider-thread-count="3">
```
or from the command line with `-dataproviderthreadcount 3`. With `parallel = true` each row needs its own driver (the `ThreadLocal` pattern).

## 6. Listeners and extras

| Feature | Purpose |
|---|---|
| `IDataProviderListener` | Hooks before/after the provider runs and on failure (7.x) |
| `IAnnotationTransformer` | Change a test's provider at runtime |
| Unique row names | Implement `ITest` or use `ITestResult` params so reports show readable names per row |
| `invocationCount` | Different from DataProvider: repeats the same test N times |

## 7. DataProvider vs the alternatives

| Need | Use |
|---|---|
| Many data rows, same test | `@DataProvider` |
| One value per run from the suite file | `@Parameters` + `@Optional("default")` with `testng.xml` |
| Value from the CI command line | `System.getProperty` (what you used) |
| Excel/CSV/JSON/DB data | `@DataProvider` returning `Iterator<Object[]>` loaded from the source |

## Interview answer

> "A DataProvider supplies multiple data sets to one test. 
It returns `Object[][]` or an `Iterator` for lazy loading. 
It supports `name`, `parallel`, `indices`, and newer options like `propagateFailureAsTestFailure`. 
The provider method can take `Method` or `ITestContext` to give per-test data. 
I can keep providers in a separate class with `dataProviderClass` (static), run rows in parallel with `data-provider-thread-count`, 
and feed it from Excel, JSON or DB. For environment values like headless, I read them from system properties or `testng.xml` rather than hardcoding."

Likely follow-up questions: the difference between `@Parameters` and `@DataProvider`, how to run only failed rows (`indices` or `testng-failed.xml`), 
and how to make parallel data-driven tests thread-safe.