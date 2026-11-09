package com.example.apiPavanSir.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC4DeleteReq {

    @Test
    public void deleteUser(){

                given()
                        .contentType("application/json")
                .when()
                        .delete("https://reqres.in/api/users/2")
                .then()
                        .log().all();

    }


}
