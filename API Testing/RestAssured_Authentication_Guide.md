# RestAssured Authentication Automation — Java Guide

RestAssured supports most real-world API authentication schemes either natively (via `RequestSpecification` builders) or through custom headers/filters. 
Below are the common types with working Java examples.

---

## 1. Basic Authentication

Two variants: **preemptive** (sends credentials with the first request) and **challenged** (waits for a 401 before sending credentials).

```java
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BasicAuthTest {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://api.example.com";

        // Preemptive Basic Auth — sends Authorization header immediately
        Response response = given()
                .auth().preemptive().basic("username", "password")
                .when()
                .get("/secure/endpoint")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response.asPrettyString());

        // Non-preemptive (challenged) Basic Auth — waits for server's 401 challenge
        given()
                .auth().basic("username", "password")
                .when()
                .get("/secure/endpoint")
                .then()
                .statusCode(200);
    }
}
```

---

## 2. Digest Authentication

```java
import static io.restassured.RestAssured.given;

public class DigestAuthTest {
    public static void main(String[] args) {
        given()
                .auth().digest("username", "password")
                .when()
                .get("https://api.example.com/secure/digest-endpoint")
                .then()
                .statusCode(200);
    }
}
```

---

## 3. Bearer Token Authentication

Common for JWT / OAuth2 access tokens issued out-of-band.

```java
import static io.restassured.RestAssured.given;

public class BearerTokenTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...";

        given()
                .auth().oauth2(token)      // adds "Authorization: Bearer <token>"
                .when()
                .get("https://api.example.com/secure/bearer-endpoint")
                .then()
                .statusCode(200);

        // Equivalent manual approach (useful when you need full control of the header)
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.example.com/secure/bearer-endpoint")
                .then()
                .statusCode(200);
    }
}
```

---

## 4. OAuth 2.0 — Client Credentials Flow (fetch token, then reuse)

In practice you first hit the token endpoint, extract the `access_token`, then attach it to subsequent calls.

```java
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OAuth2ClientCredentialsTest {

    public static void main(String[] args) {
        String tokenUrl = "https://auth.example.com/oauth/token";
        String clientId = "your-client-id";
        String clientSecret = "your-client-secret";

        // Step 1: Get access token
        Response tokenResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .when()
                .post(tokenUrl);

        String accessToken = tokenResponse.jsonPath().getString("access_token");

        // Step 2: Use token for secured API call
        given()
                .auth().oauth2(accessToken)
                .when()
                .get("https://api.example.com/secure/resource")
                .then()
                .statusCode(200);
    }
}
```

---

## 5. API Key Authentication

API keys are typically sent as a custom header or a query parameter — RestAssured has no dedicated method, so you add them directly.

```java
import static io.restassured.RestAssured.given;

public class ApiKeyAuthTest {
    public static void main(String[] args) {
        String apiKey = "sk_live_abc123xyz";

        // As a header
        given()
                .header("x-api-key", apiKey)
                .when()
                .get("https://api.example.com/secure/data")
                .then()
                .statusCode(200);

        // As a query parameter
        given()
                .queryParam("api_key", apiKey)
                .when()
                .get("https://api.example.com/secure/data")
                .then()
                .statusCode(200);
    }
}
```

---

## 6. Form-Based Authentication (Session/Cookie login)

Log in through a form endpoint, capture the session cookie, and reuse it.

```java
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class FormAuthTest {
    public static void main(String[] args) {
        CookieFilter cookieFilter = new CookieFilter(); // persists cookies across requests

        // Step 1: Login via form submission
        given()
                .filter(cookieFilter)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "myuser")
                .formParam("password", "mypassword")
                .when()
                .post("https://app.example.com/login")
                .then()
                .statusCode(200);

        // Step 2: Reuse the same session for subsequent calls
        Response response = given()
                .filter(cookieFilter)
                .when()
                .get("https://app.example.com/dashboard")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response.asPrettyString());
    }
}
```

---

## 7. Cookie-Based Authentication (manual cookie injection)

Useful when you already have a valid session ID (e.g., extracted from a browser or a prior login call).

```java
import static io.restassured.RestAssured.given;

public class CookieAuthTest {
    public static void main(String[] args) {
        given()
                .cookie("JSESSIONID", "3A9F1C2E7B8D4A5F6C0E1D2B3A4F5C6D")
                .when()
                .get("https://app.example.com/secure/profile")
                .then()
                .statusCode(200);
    }
}
```

---

## 8. NTLM Authentication

Requires the `rest-assured` NTLM dependency (uses Apache HttpClient under the hood).

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.14</version>
</dependency>
```

```java
import static io.restassured.RestAssured.given;

public class NtlmAuthTest {
    public static void main(String[] args) {
        given()
                .auth().ntlm("username", "password", "workstation", "domain")
                .when()
                .get("https://intranet.example.com/secure/resource")
                .then()
                .statusCode(200);
    }
}
```

---

## 9. Custom Dynamic Token via RestAssured Filter (reusable for JWT/OAuth pipelines)

For test suites, it's common to build a filter that fetches/refreshes a token automatically and attaches it to every request.

```java
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthTokenFilter implements Filter {

    private String cachedToken;

    private String fetchToken() {
        // Call your auth server here and return a fresh token
        Response tokenResponse = io.restassured.RestAssured
                .given()
                .contentType("application/json")
                .body("{ \"clientId\": \"abc\", \"clientSecret\": \"xyz\" }")
                .post("https://auth.example.com/token");

        return tokenResponse.jsonPath().getString("access_token");
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                            FilterableResponseSpecification responseSpec,
                            FilterContext ctx) {
        if (cachedToken == null) {
            cachedToken = fetchToken();
        }
        requestSpec.header("Authorization", "Bearer " + cachedToken);
        return ctx.next(requestSpec, responseSpec);
    }
}
```

Usage:

```java
import static io.restassured.RestAssured.given;

public class UseTokenFilterTest {
    public static void main(String[] args) {
        given()
                .filter(new AuthTokenFilter())
                .when()
                .get("https://api.example.com/secure/resource")
                .then()
                .statusCode(200);
    }
}
```

---

## 10. Global Auth Setup for an Entire Test Suite

Instead of repeating auth in every test, configure it once via `RequestSpecBuilder` (e.g., in a `@BeforeClass`/`@BeforeAll` method with TestNG/JUnit).

```java
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class GlobalAuthSetup {

    public static RequestSpecification requestSpec;

    public static void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .addHeader("Authorization", "Bearer " + fetchTokenSomehow())
                .setContentType("application/json")
                .build();
    }

    private static String fetchTokenSomehow() {
        return "your-fetched-token";
    }

    public static void sampleTest() {
        given()
                .spec(requestSpec)
                .when()
                .get("/secure/resource")
                .then()
                .statusCode(200);
    }
}
```

---

## Quick Reference Table

| Auth Type | RestAssured Method | Typical Use Case |
|---|---|---|
| Basic | `.auth().basic()` / `.auth().preemptive().basic()` | Legacy APIs, internal tools |
| Digest | `.auth().digest()` | Older enterprise systems |
| Bearer/OAuth2 token | `.auth().oauth2(token)` | JWT, modern REST APIs |
| OAuth2 full flow | Manual token fetch + reuse | Client credentials / password grant |
| API Key | `.header()` / `.queryParam()` | Public SaaS APIs (Stripe, SendGrid, etc.) |
| Form login | `.formParam()` + `CookieFilter` | Web apps with session-based auth |
| Cookie | `.cookie()` | Reusing an existing session |
| NTLM | `.auth().ntlm()` | Windows/enterprise intranet systems |
| Custom filter | `implements Filter` | Token refresh, dynamic auth pipelines |
| Global spec | `RequestSpecBuilder` | Shared setup across a whole test suite |

**Tip:** For CI test suites, wrap token-fetching logic in a singleton/cache (as in the filter example) so you're not requesting a new token on every single test — most auth servers rate-limit token endpoints.


--------------------------------------
Auth API links
--------------------------------------
Here's a solid list of free, open APIs you can use to practice each auth type with RestAssured:

## Basic & Digest Auth

**httpbin.org** (open source, self-hostable too)
- Basic: `https://httpbin.org/basic-auth/{user}/{passwd}`
- Digest: `https://httpbin.org/digest-auth/auth/{user}/{passwd}`
- Hidden basic auth: `https://httpbin.org/hidden-basic-auth/{user}/{passwd}`

```java
given().auth().preemptive().basic("user", "passwd")
    .when().get("https://httpbin.org/basic-auth/user/passwd")
    .then().statusCode(200);
```

## Bearer / OAuth2 Token

**ReqRes** (`https://reqres.in`) — fake login endpoint that returns a token
```java
String token = given().body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
    .contentType(JSON).post("https://reqres.in/api/login")
    .jsonPath().getString("token");

given().auth().oauth2(token).when().get("https://reqres.in/api/users/2");
```

**httpbin.org/bearer** — validates any bearer token is present
```
https://httpbin.org/bearer
```

## OAuth2 Full Flow (client credentials)

**GoRest** (`https://gorest.co.in`) — free fake REST API, needs a Bearer token you generate from their site (good for realistic token-reuse practice).

**DummyJSON auth** (`https://dummyjson.com/auth/login`) — returns access + refresh tokens, good for practicing full grant-style flows:
```java
given().body("{\"username\":\"emilys\",\"password\":\"emilyspass\"}")
    .contentType(JSON).post("https://dummyjson.com/auth/login");
```

## API Key

**OpenWeatherMap** (`https://openweathermap.org/api`) — free tier API key as query param, great for `.queryParam("appid", key)` practice.

**The Cat API / The Dog API** (`https://thecatapi.com`, `https://thedogapi.com`) — free key, header-based (`x-api-key`), no strict rate limits for testing.

## Form Login / Cookie-based session

**the-internet.herokuapp.com** (Elemental Selenium's open source practice site — has a form login page)
- `https://the-internet.herokuapp.com/login` — form login with cookies; good for `.formParam()` + `CookieFilter`.

**httpbin.org/cookies** — set/get cookies to simulate session reuse:
```
https://httpbin.org/cookies/set/sessionid/abc123
https://httpbin.org/cookies
```

---

**Quick picks by row:**

| Auth Type | Recommended free API |
|---|---|
| Basic/Digest | httpbin.org |
| Bearer/OAuth2 token | reqres.in or dummyjson.com |
| OAuth2 full flow | dummyjson.com/auth or gorest.co.in |
| API Key | thecatapi.com or openweathermap.org |
| Form login + Cookie | the-internet.herokuapp.com/login |
| Cookie reuse | httpbin.org/cookies |

A tip: **httpbin.org** alone covers basic, digest, bearer, and cookies — it's the single best all-purpose sandbox and is open source (you can even Docker-run your own instance via `kennethreitz/httpbin`), so it won't go down or rate-limit you mid-test-suite.

Want me to put together a sample RestAssured test class wiring up a few of these (e.g., one test per auth type)?
