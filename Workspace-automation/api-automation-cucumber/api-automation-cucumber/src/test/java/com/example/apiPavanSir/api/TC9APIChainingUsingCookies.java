package com.example.apiPavanSir.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC9APIChainingUsingCookies {

    @Test
    public void testCookies(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","");
        jsonObject.put("password","");


        Response response=  given()
                .contentType("application/json")
                .body(jsonObject)
        .when()
                .post("http://localhost:8081/secure/SetupDatabase.jspa");

        System.out.println(response.statusCode());
        System.out.println(response.getBody().jsonPath().prettify());
        String sessionID = response.getCookies().get("JSESSIONID"); // got the Jiro log in session ID

        Response responseJiraIssue =  given().contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionID) // API chaining: to create another Jira issue we are getting logged in by the previous sessions session ID passing as part of header as Cookie
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"id\": \"10110\"\n" +
                        "       },\n" +
                        "       \"summary\": \"No REST for the Wicked.\",\n" +
                        "       \"description\": \"Creating of an issue using IDs for projects and issue types using the REST API\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"id\": \"1\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}\n")
                .post("http://localhost:8080/rest/api/2/issue/");

        System.out.println(responseJiraIssue.getBody().jsonPath().prettify()); //printing response in prettify format
    }
}
