package com.example.apiPavanSir.api;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TC2PostReq_HashMap {

int id;
    @Test(enabled = false)
    public void createUser(){
        HashMap dataString = new HashMap();
        dataString.put("name","ram");
        dataString.put("job", "Trianier");

                 given()
                         .contentType("application/json")
                         .body(dataString)
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201).log().all();
    }

}
