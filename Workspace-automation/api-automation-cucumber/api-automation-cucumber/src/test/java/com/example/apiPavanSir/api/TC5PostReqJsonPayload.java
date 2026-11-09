package com.example.apiPavanSir.api;


import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC5PostReqJsonPayload {

    @Test(enabled = true)
    public void createUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","ram");
        jsonObject.put("job","TEST");


                 given()
                         .contentType("application/json")
                         .body(jsonObject)
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201).log().all();
    }

}
