# API Automation Framework — Cucumber BDD (RestAssured + TestNG + Maven + Jackson)

BDD-style API test automation for `https://jsonplaceholder.typicode.com/posts`,
written as Gherkin `.feature` files (Given/When/Then) with Java step definitions.
No authentication is required by this API.

## Tech stack
- **Maven** – build & dependency management
- **Cucumber (`cucumber-java` + `cucumber-testng`)** – Gherkin/BDD engine, TestNG runner
- **RestAssured** – HTTP client for REST API calls
- **TestNG** – underlying test runner/assertions used by Cucumber
- **Jackson `jackson-databind` (`ObjectMapper`)** – JSON <-> POJO serialization/deserialization

## Project structure
```
api-automation-cucumber/
├── pom.xml
├── src
│   ├── main/java/com/example
│   │   ├── pojo/Post.java              # POJO mapped to the request/response JSON
│   │   └── utils/JsonUtils.java        # ObjectMapper wrapper (toJson / fromJson)
│   └── test
│       ├── java/com/example
│       │   ├── stepdefs/PostSteps.java # Given/When/Then step definitions
│       │   └── runner/TestRunner.java  # Cucumber-TestNG runner (@CucumberOptions)
│       └── resources
│           ├── features/posts.feature  # Gherkin scenarios
│           └── testng.xml              # TestNG suite -> points at TestRunner
└── README.md
```

## Feature file: `posts.feature`
```gherkin
Feature: Posts API

  Background:
    Given the Posts API base URL is set

  @create
  Scenario: Create a new post
    Given I have a new post with title "foo", body "bar" and userId 1
    When I send a POST request to "/posts"
    Then the response status code should be 201
    And the response post title should be "foo"
    And the response post body should be "bar"
    And the response post userId should be 1

  @read
  Scenario: Fetch an existing post by id
    Given a post with id 1 already exists
    When I send a GET request to "/posts/1"
    Then the response status code should be 200
    And the response post id should be 1

  @update
  Scenario: Update an existing post
    Given I have updated post data with id 1, title "updated title", body "updated body" and userId 1
    When I send a PUT request to "/posts/1"
    Then the response status code should be 200
    And the response post title should be "updated title"
    And the response post body should be "updated body"

  @delete
  Scenario: Delete an existing post
    Given a post with id 1 already exists
    When I send a DELETE request to "/posts/1"
    Then the response status code should be 200
```

## How it works
1. **Given** steps build the request payload as a `Post` POJO (no hardcoded JSON strings).
2. **When** steps serialize the POJO to JSON with Jackson's `ObjectMapper`
   (`JsonUtils.toJson`), fire the RestAssured call, then deserialize the JSON
   response back into a `Post` (`JsonUtils.fromJson`) for type-safe assertions.
3. **Then/And** steps assert on the typed `Post` fields and the HTTP status code.
4. **No authentication** — `jsonplaceholder.typicode.com` is a free/public mock
   API, so no auth headers are set anywhere in the framework.

## How to run
```bash
# runs the TestNG suite, which triggers the Cucumber runner + all .feature files
mvn clean test
```

Run only a subset of scenarios by tag (uncomment `tags` in `TestRunner.java`), e.g.:
```java
tags = "@create or @read"
```

### Reports
After running, Cucumber reports are generated at:
- `target/cucumber-reports/cucumber.html`
- `target/cucumber-reports/cucumber.json`

## Sample console output
```
Scenario: Create a new post
  Given the Posts API base URL is set ... PASSED
  Given I have a new post with title "foo", body "bar" and userId 1 ... PASSED
  When I send a POST request to "/posts" ... PASSED
  Then the response status code should be 201 ... PASSED
  And the response post title should be "foo" ... PASSED
  And the response post body should be "bar" ... PASSED
  And the response post userId should be 1 ... PASSED
```

> Note: jsonplaceholder is a mock API — POST/PUT/DELETE calls are accepted and
> return success responses, but changes are not actually persisted server-side.
