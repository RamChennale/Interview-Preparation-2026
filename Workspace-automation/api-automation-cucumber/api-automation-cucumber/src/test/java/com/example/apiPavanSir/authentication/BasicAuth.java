package com.example.apiPavanSir.authentication;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class BasicAuth {

    @Test
    public void basicAuth(){
        given()
                .contentType("application/json")
                .auth().basic("admin","password")
                .when()
                .get("http://localhost/basic-auth/admin/password")
                .then().log().all();

    }
}
