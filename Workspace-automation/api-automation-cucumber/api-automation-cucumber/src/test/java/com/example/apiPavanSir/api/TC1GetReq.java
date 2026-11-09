package com.example.apiPavanSir.api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TC1GetReq {


    @Test(enabled = false)
    public void geteUser() {
        given().contentType("application/json")
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }

    @Test(enabled = true)
    public void getAllUsers(){
            when().get("https://bookstore.toolsqa.com/BookStore/v1/Book")
                    .then().log().all();
    }
}
