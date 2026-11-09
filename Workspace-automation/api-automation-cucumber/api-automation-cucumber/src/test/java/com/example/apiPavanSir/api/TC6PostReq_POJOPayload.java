package com.example.apiPavanSir.api;


import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC6PostReq_POJOPayload {

    @Test(enabled = true)
    public void createUser(){

        POJO_ReqData reqData = new POJO_ReqData();
        reqData.setName("Ram Chennale");
        reqData.setJob("QA SDET Engineer");

                 given()
                         .contentType("application/json")
                         .body(reqData)
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201).log().all();
    }

}
