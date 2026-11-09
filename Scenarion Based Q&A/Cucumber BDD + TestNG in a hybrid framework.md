##Cucumber BDD + TestNG in a hybrid framework

***Yes, I use them together. 
***Cucumber*** handles the behavior layer: Gherkin features, step definitions, tags and hooks. 
***TestNG*** is the execution engine through AbstractTestNGCucumberTests: parallel runs, suite control, listeners, assertions and CI integration. 

I use Scenario Outline for data-driven cases, tags for smoke and regression selection, 
TestNG testng.xml and Maven for Jenkins runs, and ThreadLocal for thread-safe drivers. 

The main thing to watch is that TestNG’s method-level annotations don’t apply inside step definitions, so scenario-level setup goes into Cucumber hooks.”

I split by scope. TestNG annotations on the runner class handle suite-level work: config load, report initialization and flush, and listeners. Cucumber hooks handle everything scenario-level: driver creation and quit, navigation, screenshots on failure using the Scenario object, and tag-based setup like @Before("@api") for token generation. I prefer hooks for scenario work because they give access to the scenario name, tags and status, and they run correctly in parallel with ThreadLocal drivers. I also use the order attribute when I need a defined sequence, for example driver first, then login. One thing I avoid is putting TestNG’s @BeforeMethod in step or hook classes, because it doesn’t apply there.”


#how to run tests in parallel for Cucumber BDD framework if not suing testNG ?
Running Cucumber tests in parallel without TestNG

Cucumber doesn’t depend on TestNG. It runs on JUnit (4 or 5), and parallel execution is built into Cucumber-JVM from v7 onward. 
The modern, recommended way is the JUnit 5 platform engine.

“Cucumber doesn’t need TestNG for parallel runs. 

***I use the JUnit 5 ***Cucumber engine and enable cucumber.execution.parallel.enabled in junit-platform.properties, with a fixed thread pool, 
so scenarios run in parallel. 

***For legacy JUnit 4 ***setups I use Surefire with forkCount and separate runners. To scale further, 
I split suites by tags across Jenkins parallel stages and run browsers on Selenium Grid or Docker. 

In every case I keep the framework thread-safe: ThreadLocal<WebDriver>, PicoContainer for per-scenario state, and independent test data.”



###############TestNG testng.xml parallel with Cucumber: how it works ??


"Yes, I run Cucumber with TestNG parallel. 
The runner extends `AbstractTestNGCucumberTests`, and I override `scenarios()` with `@DataProvider(parallel = true)`, 
then set `data-provider-thread-count` in `testng.xml` to control how many scenarios run together. 
I use `parallel="tests"` when I want separate blocks per browser or tag. The key point is that the `parallel` attribute alone doesn't parallelize scenarios, 
because Cucumber feeds them through the DataProvider. To make it safe, I use `ThreadLocal<WebDriver>`, 
PicoContainer for per-scenario state, unique test data per scenario, and thread-safe reporting. 
I like this approach because I keep TestNG's listeners, retry, and suite control."


A natural next question is **"How do you share state between steps with PicoContainer in parallel runs?"** I can prepare that one next if you'd like.





# TestNG `testng.xml` parallel with Cucumber: how it works

**Yes, it works and is very common**, but there is one catch that many people miss. 
For Cucumber, the `parallel` attribute in `testng.xml` alone does **not** run scenarios in parallel. 
Scenarios are fed to TestNG through a `@DataProvider`, so the DataProvider must be parallel too.

## The correct setup

**1. Runner class:** make the DataProvider parallel.
```java
@CucumberOptions(features = "src/test/resources/features",
                 glue = {"stepdefs", "hooks"},
                 tags = "@regression",
                 plugin = {"pretty", "json:target/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
```

**2. `testng.xml`:** set the thread count for the DataProvider.
```xml
<suite name="CucumberSuite" data-provider-thread-count="4">
    <test name="Regression">
        <classes>
            <class name="runners.TestRunner"/>
        </classes>
    </test>
</suite>
```

`data-provider-thread-count` is the setting that controls how many scenarios run at once. The default is 10 if you don't set it.

## What each `parallel` value does with Cucumber

| `testng.xml` setting | Effect on Cucumber |
|---|---|
| `data-provider-thread-count` + `@DataProvider(parallel=true)` | **Scenario-level parallelism.** This is the main one to use. |
| `parallel="classes"` + `thread-count` | Runs **different runner classes** in parallel. Useful only if you have multiple runners (for example one per tag or module). One runner class gives no benefit. |
| `parallel="tests"` + `thread-count` | Runs each `<test>` block in parallel. Good for giving each block a different runner, tag, or browser parameter. |
| `parallel="methods"` | Little value here, since the runner has a single `@Test` method. |

A useful pattern is `parallel="tests"` with one `<test>` per browser (`<parameter name="browser" value="chrome"/>`), plus a parallel DataProvider inside each, which gives you cross-browser and scenario-level parallelism together.

## Pros

- **Simple:** two small changes (the DataProvider flag and the XML), no extra dependencies.
- **Scenario-level parallelism** with good thread utilisation.
- **Full TestNG ecosystem remains:** listeners, `IRetryAnalyzer`, `SoftAssert`, suite XML, groups, parameters.
- **Familiar to most enterprise teams** and easy to trigger from Maven and Jenkins (`-DsuiteXmlFile=testng.xml`).

## Cons and watch-outs

- **Thread safety is your responsibility.** Use `ThreadLocal<WebDriver>`, `ThreadLocal` for Extent test objects, and PicoContainer for per-scenario state.
- **Hooks and listeners must be thread-safe:** no shared static variables for scenario data.
- **Scenario retry is awkward.** `IRetryAnalyzer` doesn't map cleanly to scenarios, so use Cucumber's `rerun` plugin and a second runner.
- **Reports can get messy:** the Cucumber JSON report is fine, but custom reporters need care in parallel.
- **Shared data conflicts:** scenarios that use the same user account or modify the same record will fail randomly in parallel, so give each scenario unique data.
- **Version note:** from Cucumber 7, `cucumber-testng` supports this officially, but the Cucumber engine's own `cucumber.execution.parallel` property applies only to the JUnit 5 engine, not to TestNG.

## TestNG parallel vs JUnit 5 Cucumber engine

| | TestNG DataProvider parallel | JUnit 5 Cucumber engine |
|---|---|---|
| Setup | `@DataProvider(parallel=true)` + XML | `junit-platform.properties` |
| Thread control | `data-provider-thread-count` | `parallelism`, `max-pool-size` |
| Retry, listeners, groups | Rich TestNG support | More limited |
| Best for | Teams already on TestNG, Jenkins suites | New Cucumber-only frameworks |

## Interview-ready answer

"Yes, I run Cucumber with TestNG parallel. The runner extends `AbstractTestNGCucumberTests`, and I override `scenarios()` with `@DataProvider(parallel = true)`, then set `data-provider-thread-count` in `testng.xml` to control how many scenarios run together. I use `parallel="tests"` when I want separate blocks per browser or tag. The key point is that the `parallel` attribute alone doesn't parallelize scenarios, because Cucumber feeds them through the DataProvider. To make it safe, I use `ThreadLocal<WebDriver>`, PicoContainer for per-scenario state, unique test data per scenario, and thread-safe reporting. I like this approach because I keep TestNG's listeners, retry, and suite control."

A natural next question is **"How do you share state between steps with PicoContainer in parallel runs?"** I can prepare that one next if you'd like.

VERY IMP Frameworkm and Scenarios based QA 

How to handel dynamic web table ?

diff betwen POM.xml an testNG.xml ?

How parallel TE achived in TestNG ?

How to provid the executable jar of your working framework to the other team member ? 

What's Abstract class explain, where did you used in you project ?

diff between WebDriver and RemoteWebDriver?

how did you launch the ChromeDriver ? as dynamic way which ever browser wanted to FF, Edge ?

what is extendable class and where did you implemeted and used it ?

By SQL query get valiue from DB and add/insert the value to Excel file ? 
	connect to DB what's process, by JDBC and ResultSet and and read value from query and write data to string - and write the same String value to the Excel sheet cell data ?
 
By SQl query get data and store value to Excel sheet  
	1. connect to DB - JDBC dependacy how to connec tDB
		connection con = DB name and table 
		hit the quyer - get ResultSet
		extrat valye frm ResultSet save it in String and 
		close the DB connec

	2.  Open the file 
		read the value from string
		get the sheet name, fileOutput strema to write to excel sheet 

Multilanguage testing in your framework

What key value pair, where did you implemented this in frameworks ?

whaich dev env have used to devlope the AT script - eclipse / maven to compile, Gitlabe 

What is significance of .M2 folder of  maven ? how it helps in 

can we rename the .m2 folder, if i rename what will happen ?

did you pushed the code to git ?
before push 
pull 

how did you push the code the Git? how the review and process happen 

explain Maven build lifecycle? 
 




  






Note: to do 





