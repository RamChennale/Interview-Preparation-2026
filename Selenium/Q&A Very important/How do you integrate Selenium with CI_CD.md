**12. How do you integrate Selenium with CI/CD, and how do you run at scale?**

	Trigger suites via Jenkins/GitHub Actions/GitLab CI pipelines on PR merge or nightly schedule as sprint release. 
	
	Use a Dockerized Selenium Grid (Hub + Node containers) or cloud grid providers for scale and true cross-browser/cross-OS coverage. 
	
	Parallelize via TestNG suite XML or JUnit5 parallel execution, and 
	
	publish Allure/ExtentReports as pipeline artifacts. 
	
	For flaky-test control, integrate a retry analyzer and quarantine flaky tests into a separate CI stage so they don't block the pipeline.
