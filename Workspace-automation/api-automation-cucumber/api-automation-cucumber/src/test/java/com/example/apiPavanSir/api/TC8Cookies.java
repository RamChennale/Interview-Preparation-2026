package com.example.apiPavanSir.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Map;

import static io.restassured.RestAssured.*;
public class TC8Cookies {

    @Test(enabled = false)
    public void testCookies(){

        Response response =  when().get("https://www.google.com/");
        Map<String, String>  cookieMap=  response.getCookies();

        for (String key:cookieMap.keySet()){
                String cookieValue = response.getCookie(key);
                System.out.println(key + "    "+cookieValue);
        }
    }

    @Test(enabled = false)
    public void getCookie(){
        Response response =  when().get("https://www.google.com/");
       response.getCookies();
    }


}
