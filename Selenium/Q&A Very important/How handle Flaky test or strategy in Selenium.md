# Flaky test strategy to handle ? 
 
A flaky test passes and fails on the same code without any change. They’re dangerous because they erode trust, and people start ignoring red builds.

I treat flakiness as an engineering problem with a process. 

1. First, 
detect: I track pass/fail history across CI runs, using Allure or ReportPortal trends, to identify tests with inconsistent results rather than relying on gut feel. 

2. Second, 
classify the root cause. In my experience it’s usually one of five things: synchronization and timing, shared or dirty test data, order dependency between tests, 
	environment instability like slow services or unstable test environments, or fragile locators.

3. Third, 
quarantine: I tag known-flaky tests so they don’t block the pipeline, but they stay visible with an owner and a deadline. I don’t silently delete or ignore them. 

4. Fourth, 
	fix the cause: replace sleeps with condition-based waits, create test data through API calls instead of the UI, make every test independent, and 
	use a ThreadLocal driver for parallel runs.

I do use a retry analyzer in TestNG, but only as a safety net with a cap of one or two retries, and every retry is logged and reported. 
Retries without reporting just hide the problem.

Using this approach on our regression suite, we cut flaky failures substantially and reduced the time the team spent re-running and investigating false failures.”

# Retry block of code to handle failed Flaky test

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            System.out.println("Retrying: " + result.getName() + " attempt " + count);
            return true;
        }
        return false;
    }
}