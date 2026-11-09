package com.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Central place for JSON serialization / deserialization using
 * Jackson's ObjectMapper. Keeps a single shared instance so it's
 * not re-created for every call.
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
        // utility class, no instances
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /** Serialize a POJO -> JSON string (used as the request body). */
    public static String toJson(Object pojo) {
        try {
            return OBJECT_MAPPER.writeValueAsString(pojo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize object to JSON", e);
        }
    }

    /** Deserialize a JSON string -> POJO (used to read the response body). */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize JSON to object", e);
        }
    }
}
