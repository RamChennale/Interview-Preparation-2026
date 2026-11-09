RequestSpecification and 
# RequestSpecification

A **RequestSpecification** is a reusable, pre-configured set of request settings in RestAssured, 
such as base URI, headers, content type, auth, and query params. 

You define it once and apply it to any request with `.spec(...)`, instead of repeating the same setup in every test.

## Without vs with

**Without (repetition in every test):**

```java
given()
    .baseUri("https://api.example.com")
    .contentType(ContentType.JSON)
    .header("Authorization", "Bearer " + token)
.when()
    .get("/users/2")
.then()
    .statusCode(200);
```

**With (define once, reuse):**

```java
RequestSpecification reqSpec = new RequestSpecBuilder()
        .setBaseUri("https://api.example.com")
        .setContentType(ContentType.JSON)
        .addHeader("Authorization", "Bearer " + token)
        .build();

given().spec(reqSpec)
.when().get("/users/2")
.then().statusCode(200);
```

## What it can hold

Base URI and base path, headers and cookies, content type, authentication, query and path params, logging (`log().all()`), and 
filters (for example, Allure reporting).

## Related: ResponseSpecification

The same idea for responses. It holds common assertions, such as expected status code, content type, or response time, applied with `.spec(resSpec)` in the `then()` section.

```java
ResponseSpecification resSpec = new ResponseSpecBuilder()
        .expectStatusCode(200)
        .expectContentType(ContentType.JSON)
        .build();

given().spec(reqSpec)
.when().get("/users/2")
.then().spec(resSpec)
      .body("id", equalTo(2));
```

## Key points

- Built using **RequestSpecBuilder** (`.build()` returns the RequestSpecification).
- You can add extra settings on top of a spec in a specific test, such as `given().spec(reqSpec).pathParam(...)`.
- It keeps tests short, and a change like a new header or base URL is made in one place.

## Interview answer

"A RequestSpecification is a reusable request template in RestAssured. 
I define common settings like base URI, headers, content type, and the auth token once using RequestSpecBuilder, and apply them to every test with `.spec()`. 
It removes duplication and makes the framework easier to maintain. Similarly, a ResponseSpecification holds common response assertions"
