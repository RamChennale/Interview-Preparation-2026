package com.example.apiPavanSir.PathQueryParam;
import org.testng.annotations.Test;

import  static io.restassured.RestAssured.*;

public class PathQueryParameter {

    @Test
    public void queryParameter(){

        //1: https://reqres.in/api/users?page=2 2: https://reqres.in/api/users?page=2&id=7

        given()
                .pathParams("mypath","users") // path param setting, need pass the name of parameter explicitly
                .queryParam("page",2) //queryParam by default attached to the URL on set
                .queryParam("id",7)

                .when()
                    .get("https://reqres.in/api/{mypath}")
                    .then()
                .statusCode(200).log().all();
    }
}
