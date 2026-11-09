#what is endpoint 

An endpoint is a specific URL/web-address on a server where an API exposes a resource or operation. 
It’s the combination of the ***base URL,  path, and the HTTP method.*** 
For example, GET https://api.example.com/v1/clients/123 is an endpoint that retrieves client 123

An endpoint has havig: 
***the base URL, 
***the resource path, 
***path parameters (/clients/{id}), query parameters (?status=active&page=2), 
***the HTTP method, 
If we say complete endpoint URL with cURL(stands for Client URL), which includes
***headers (auth token, content-type), 
***the request body which has payload.

given()
    .baseUri("https://api.example.com")
    .header("Authorization", "Bearer " + token)
    .contentType(ContentType.JSON)
    .body(payload)
.when()
    .post("/v1/clients")
.then()
    .statusCode(201)
    .body("clientId", notNullValue())
    .body("status", equalTo("ACTIVE"));
	
	
#follow-up questions

#Endpoint vs API? 
	An API is the whole interface (the set of endpoints plus rules); 
	An endpoint is one specific access point within it.
#Endpoint vs URL? 
	Every endpoint has a URL, but an endpoint also implies the HTTP method and the behavior attached to it.
#PUT vs PATCH? 
	PUT replaces the whole resource; PATCH updates part of it.
#How do you handle authentication in endpoint tests? 
	Fetch the token once in a setup method (for example @BeforeClass), reuse it, and 
	test expired and invalid tokens as negative cases.
#How do you test an endpoint with no documentation? 
	Use Postman or browser dev tools to observe traffic, then check for a Swagger/OpenAPI spec.
	