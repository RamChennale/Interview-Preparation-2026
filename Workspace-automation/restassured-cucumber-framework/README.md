# RestAssured + Cucumber (BDD) + TestNG API Automation Framework

End-to-end API test automation framework for `https://reqres.in/api` covering
Create, Read (all/single), Update, and Delete operations, each exercising a
different authentication mechanism, driven by Gherkin feature files and
executed via TestNG, wired into a Jenkins regression pipeline.

## Tech Stack

| Layer            | Tool/Library                          |
|-------------------|----------------------------------------|
| Language          | Java 17                                |
| API Automation    | RestAssured 5.4                        |
| BDD               | Cucumber-JVM 7.15 (Gherkin)            |
| Execution Engine  | TestNG 7.9                             |
| Build Tool        | Maven                                  |
| CI/CD             | Jenkins (declarative pipeline)         |
| Logging           | Log4j2                                 |
| Reporting         | Cucumber HTML/JSON + ExtentReports     |
| DI (step sharing) | Cucumber-Picocontainer                 |

## Architecture / OOP Design

```
src/main/java/com/framework/
├── auth/
│   ├── IAuthenticationStrategy.java   -> Strategy interface (abstraction)
│   ├── BasicAuthStrategy.java         -> Basic Auth (username/password)
│   ├── JwtAuthStrategy.java           -> JWT / Bearer token
│   ├── ApiKeyAuthStrategy.java        -> API Key (query param or header)
│   ├── NoAuthStrategy.java            -> Null-object pattern
│   └── AuthenticationFactory.java     -> Factory pattern (creates the right strategy)
├── base/
│   └── BaseApiClient.java             -> Abstract class: get/post/put/delete primitives
├── clients/
│   └── UserApiClient.java             -> Concrete client (inherits BaseApiClient)
├── config/
│   └── ConfigManager.java             -> Singleton config loader
├── models/
│   └── User.java                      -> POJO with nested Builder pattern
└── utils/
    └── RequestSpecBuilderUtil.java    -> Shared RequestSpecification builder

src/test/java/com/framework/
├── runners/TestNGRunner.java          -> Cucumber-TestNG bridge, tag filtering
├── stepdefinitions/
│   ├── Hooks.java                     -> @Before/@After lifecycle
│   └── UserSteps.java                 -> Gherkin step implementations
└── context/TestContext.java           -> Scenario-scoped shared state (DI)
```

### OOP concepts demonstrated
- **Encapsulation** – all fields private with controlled getters/setters (`User`, `TestContext`, `ConfigManager`).
- **Abstraction** – `IAuthenticationStrategy` and abstract `BaseApiClient` hide implementation detail from callers.
- **Inheritance** – `UserApiClient extends BaseApiClient`.
- **Polymorphism** – `AuthenticationFactory` returns different `IAuthenticationStrategy` implementations at runtime; the client code calls `applyAuthentication()` without knowing the concrete type.
- **Design Patterns** – Singleton (`ConfigManager`), Factory (`AuthenticationFactory`), Strategy (auth classes), Builder (`User`), Null Object (`NoAuthStrategy`).

## Auth mapping to requirements

| Endpoint                          | Method | Auth type | Strategy class      |
|------------------------------------|--------|-----------|----------------------|
| `/api/users`                       | POST   | JWT       | `JwtAuthStrategy`    |
| `/api/users/2`                     | PUT    | JWT       | `JwtAuthStrategy`    |
| `/api/users?page=1`                | GET    | JWT       | `JwtAuthStrategy`    |
| `/api/users/2?Test=Test12345`      | GET    | API Key   | `ApiKeyAuthStrategy` |
| `/api/users/2`                     | DELETE | Basic     | `BasicAuthStrategy`  |

## Running locally

```bash
# Run full regression suite (default tag @Regression)
mvn clean test

# Run only smoke scenarios
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Run a single feature category
mvn clean test -Dcucumber.filter.tags="@Create"

# Target a different environment (reads System property in ConfigManager overrides)
mvn clean test -Denv=STAGE
```

Reports are generated under `target/cucumber-reports/` (HTML, JSON, JUnit XML)
and `target/logs/execution.log`.

## Jenkins CI/CD

The `Jenkinsfile` defines a declarative pipeline with parameters:
- `TEST_TAGS` – choose which Cucumber tag to execute (`@Regression`, `@Smoke`, etc.)
- `ENVIRONMENT` – QA / STAGE / PROD

Pipeline stages: **Checkout → Build → Run Regression Tests → Publish Reports**,
with a nightly cron trigger (`H 2 * * *`) plus artifact archiving and
HTML/Cucumber/TestNG report publishing (requires the *Cucumber Reports*,
*HTML Publisher*, and *TestNG Results* Jenkins plugins).

## Extending the framework

To add a new authentication type (e.g. OAuth2):
1. Create `OAuth2AuthStrategy implements IAuthenticationStrategy`.
2. Add `OAUTH2` to `AuthType` enum.
3. Add a `case OAUTH2 -> new OAuth2AuthStrategy();` to `AuthenticationFactory`.

No other class needs to change (Open/Closed Principle).
