
**13. How do you take screenshots on test failure automatically?**
Implement `ITestListener.onTestFailure()` (TestNG) or a JUnit5 `TestWatcher` extension that calls `((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE)` 
	and attaches it to the report (Extent/Allure) with the test name and timestamp.
