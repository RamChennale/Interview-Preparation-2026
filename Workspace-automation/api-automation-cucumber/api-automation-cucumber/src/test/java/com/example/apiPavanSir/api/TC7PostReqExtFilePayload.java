package com.example.apiPavanSir.api;

import  static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TC7PostReqExtFilePayload {

    @Test
    public void postReqJsonFile() throws FileNotFoundException {

        System.out.println(System.getProperty("user.dir"));
        File file = new File(System.getProperty("user.dir"),"\\ReqBody.json");
        FileReader  fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonObjectData = new JSONObject(jsonTokener);

        given()
                .contentType("application/json")
                .body(jsonObjectData.toString())

        .when().post("https://reqres.in/api/users")

        .then().statusCode(201).log().all();
    }
}
