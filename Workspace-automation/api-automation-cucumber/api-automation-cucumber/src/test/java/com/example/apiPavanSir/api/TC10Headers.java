package com.example.apiPavanSir.api;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static java.awt.SystemColor.text;

public class TC10Headers {

    @Test(enabled = false)
    public void getHeader(){
        Response response =  when().get("https://www.google.com/");
        String header = response.header("Content-Type");
        String text = response.header("text/html");

        System.out.println("header:  "+header + " text/html: "+text);
    }

    @Test
    public void getHeaders(){
        Response response =  when().get("https://www.google.com/");
        Headers headers = response.getHeaders();
        System.out.println("headers :  "+headers );
    }




}
