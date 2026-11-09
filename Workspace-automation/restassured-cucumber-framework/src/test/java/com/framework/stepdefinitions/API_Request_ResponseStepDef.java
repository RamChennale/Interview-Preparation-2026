package com.framework.stepdefinitions;

/* create CRUD operation API automation POST, PUT, GET, DELETE
* */

import com.framework.models.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.requestSpecification;

public class API_Request_ResponseStepDef {


    static Response response;
    static RequestSpecification requestSpecification;


    @Given("new user with name {string} and job {string}")
    public void i_have_a_new_user_with_name_and_job(String name, String job) {
        String resuestData = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
        requestSpecification = RestAssured.given()
                .header("Contet-Type", "application/json")
                .body(resuestData);
    }

    @When("POST request to create the user")
    public void i_send_a_post_request_to_create_the_user() {
        Response response1 = requestSpecification.post();

    }

    @Then("response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        int getStatusCode = response.getStatusCode();
        if (expectedStatusCode == 201)
            System.out.println("POST verified successfully");
        else
            System.out.println("POST failed "+ getStatusCode);
    }






    /*



    public void GETRequest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RequestSpecification requestSpecification =  RestAssured.given();
        Response response = requestSpecification.get("/posts/1");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        System.out.println("GET req response : "+response.getBody());

        if (statusCode==200)
            System.out.println("GET verified successfully");
        System.out.println("GET failed. Expected 200 but got " + statusCode);
    }





    public void POSTRequest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        String resuestData = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
        RequestSpecification requestSpecification = RestAssured.given()
                .header("Contet-Type", "application/json")
                .body(resuestData);
        Response response1 = requestSpecification.post("/posts");
        int getStatusCode = response1.getStatusCode();
        if (getStatusCode == 201)
            System.out.println("POST verified successfully");
        System.out.println("POST failed "+ getStatusCode);
    }


    public void PUTRequest(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        String resuestData = "{ \"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }";

        RequestSpecification requestSpecification = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(resuestData);
        Response response = requestSpecification.put("/posts/1");
        int putStatusCode = response.getStatusCode();
        if (putStatusCode == 200)
            System.out.println("PUST success "+putStatusCode);
        System.out.println("PUST failed  : "+ putStatusCode);
    }

    public void DELETERequest(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        RestAssured.basePath ="/posts";

        Response response = requestSpecification.delete("1");
        int deleteStatusCode =response.getStatusCode();
        if( deleteStatusCode== 200)
            System.out.println("DELETE success "+deleteStatusCode);
        System.out.println("DELETE failed  : "+ deleteStatusCode);
    }

*/

}
