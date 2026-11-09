package api.endpoints;

//created to perform CRUD operations req to the user API.
import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    static Response response;

       public static Response createUser(User payload){
       response =
               given()
                    .contentType("application/json")
                    .accept(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(Routes.post_url);

       return response;
    }

    public static Response readUser(String userName){
           response = given().pathParams("username", userName)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(Routes.get_url);
        return response;
    }

    public static Response updateUser(String username, User payload){
         response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", username)
                .body(payload)
                .when()
                .put(Routes.update_url);
        return response;
    }

    public static Response deleteUser(String userName){
         response =
                given()
                        .pathParams("username", userName)
                .when()
                .delete(Routes.get_url);
        return response;
    }


}
