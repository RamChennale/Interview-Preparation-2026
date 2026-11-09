package api.endpoints;

//created to perform CRUD operations req to the user API.
import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsProp {

   static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");//loads properties file
        return routes;
   }


    static Response response;

       public static Response createUser(User payload){
           String postURL = getURL().getString("post_url");
       response =
               given()
                    .contentType("application/json")
                    .accept(ContentType.JSON)
                    .body(payload)
                .when()
                    .post(postURL);

       return response;
    }

    public static Response readUser(String userName){
        String getURL = getURL().getString("get_url");
           response = given().pathParams("username", userName)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(getURL);
        return response;
    }

    public static Response updateUser(String username, User payload){
        String updateURL = getURL().getString("update_url");
           response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", username)
                .body(payload)
                .when()
                .put(updateURL);
        return response;
    }

    public static Response deleteUser(String userName){
        String deleteURL = getURL().getString("get_url");
         response =
                given()
                        .pathParams("username", userName)
                .when()
                .delete(deleteURL);
        return response;
    }


}
