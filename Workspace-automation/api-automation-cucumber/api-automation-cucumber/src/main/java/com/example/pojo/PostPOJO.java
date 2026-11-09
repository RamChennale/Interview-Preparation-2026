package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * POJO representing the request/response body for
 * https://jsonplaceholder.typicode.com/posts
 *
 * JsonIgnoreProperties -> ignores any extra fields returned by the API
 * that are not mapped here, so deserialization never breaks.
 *
 * JsonInclude.NON_NULL -> when this object is serialized back to JSON
 * (e.g. for POST), fields that are null (like "id" on creation) are
 * simply omitted from the request body instead of being sent as null.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostPOJO {

    private Integer id;
    private Integer userId;
    private String title;
    private String body;

    public PostPOJO() {
        // no-arg constructor required by Jackson
    }

    // pram-arg constructor required by Jackson
    public PostPOJO(Integer id, Integer userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
/*
toString() method in a POJO (Plain Old Java Object) primarily to make debugging,
logging, and troubleshooting significantly easier.

   And overriding 'toString()'  forces the application to return the actual values of the object's properties
   (like  id, userId, title, and body) in a clear, readable text format.
        */
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
