
**5. How do you ensure thread safety during parallel execution?**
Use `ThreadLocal<WebDriver>` to give each test thread its own driver instance instead of a static/shared driver — 
a shared driver is the cause of flaky parallel suites. 
	Configure parallel execution at the `<suite>` level in `testng.xml` (`parallel="methods"` or `"classes"`), and 
	ensure no shared mutable state (static variables, shared test data files) between threads.
