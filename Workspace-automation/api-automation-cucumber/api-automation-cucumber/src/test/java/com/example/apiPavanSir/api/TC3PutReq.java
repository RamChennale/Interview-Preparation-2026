package com.example.apiPavanSir.api;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TC3PutReq {

int id;

    @Test(enabled = true, priority = 1)
    public void createUserGetId(){
        HashMap data = new HashMap();
        data.put("name","ram");
        data.put("job", "Trianier");

       id =   given()
                .contentType("application/json")
                .body(data)
                .when().post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
    }

    @Test(enabled = true, priority = 2, dependsOnMethods = {"createUserGetId"})
    public void updateUser(){
        HashMap data = new HashMap();
        data.put("name","ram");
        data.put("job", "SDET");

          given()
                .contentType("application/json")
                .body(data)

          .when().put("https://reqres.in/api/users/"+id)
                  .then()
                  .statusCode(200)
                  .log().all();
    }
}
