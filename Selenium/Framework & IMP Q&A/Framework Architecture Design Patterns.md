Framework Architecture / Design Patterns (10-YOE differentiators)

### Q17. Which design patterns have you actually used in your automation framework?
| Pattern | Where used | Why |
|---|---|---|
| **Singleton** | `ConfigReader`, `ExtentReportManager` | One instance of config/report per JVM run |
| **Factory** | `DriverFactory` (Q5) | Decouple browser-specific driver creation from tests |
| **Builder** | Test data object creation (`User.builder().name(..).role(..).build()`) | Readable, flexible object construction for complex payloads |
| **Page Object / Fluent Interface** | Page classes (Q8) | Maintainability, readability |
| **Strategy** | Pluggable wait strategies / pluggable report strategy (Extent vs Allure) | Swap implementation without touching test code |
| **Decorator** (via listeners) | TestNG `ITestListener` | Add cross-cutting behavior (logging, screenshots) without modifying test classes |

---

### Q18. How do you integrate Selenium + TestNG with CI/CD (Jenkins) and Docker Grid?
**Answer, real pipeline flow:**
1. Code pushed → GitHub webhook triggers Jenkins job.
2. Jenkins runs `mvn clean test -Dsuite=regression.xml -Dbrowser=chrome -Dgrid=true`.
3. Tests connect to a **Selenium Grid** running via `docker-compose` (hub + chrome-node + firefox-node containers) instead of local browsers.
4. TestNG listener generates Extent HTML report + JUnit XML (for Jenkins native reporting/trend graphs).
5. Post-build: publish report as Jenkins artifact, send Slack/Teams notification with pass/fail summary and a link.
6. On failure: attached screenshots + video recording (via `Selenoid` or Grid's video-recording node) for debugging without needing to reproduce locally.

```java
DesiredCapabilities caps = new DesiredCapabilities();
caps.setCapability("browserName", "chrome");
WebDriver driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), caps);
```

---

### Q19. How do you handle environment-specific configuration (QA/Stage/Prod) cleanly?
```java
public class ConfigReader {
    private static Properties properties;

    static {
        String env = System.getProperty("env", "qa");
        try (InputStream input = new FileInputStream("src/test/resources/config/" + env + ".properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Config file not found for env: " + env, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
```
Run with `-Denv=stage` — same codebase, zero hardcoded URLs. 
This is exactly what's expected when an interviewer asks "how do you avoid hardcoding environment URLs across 200+ tests."

---