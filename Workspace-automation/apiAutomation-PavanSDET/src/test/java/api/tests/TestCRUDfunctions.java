package api.tests;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestCRUDfunctions {

    public static String base_url = "https://reqres.in/";
    Map<String, String> payload;


    @Test(enabled = false)
    public void testPost(){
        baseURI = "https://reqres.in/";
        payload = new HashMap<>();
        payload.put("name","ram");
        payload.put("job","QA");

        given()
                    .contentType("application/json")
                    .header("Content-Type","application/json")
                .body(payload)
                .when()
                    .post("/api/users")
                .then()
                    .log().all();
    }
    //https://reqres.in/api/unknown/1'

    @Test(enabled = false)
   public void testGetRequest(){
            given()
                .contentType(ContentType.JSON)
            .when()
                .get(base_url+"api/unknown/1")
            .then().log().all()
                    .statusCode(200);

   }

   @Test
   public void testPut(){
        Response response=
                given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                .when()
                    .put("https://reqres.in/api/users/")
                .then()
                    .extract().response();
        System.out.println(response.getBody().asString());
   }


}
