package com.framework.utils;

import com.framework.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Utility class (final, private constructor) responsible for building the
 * common RequestSpecification shared by all API clients - base URI, content type,
 * timeouts and logging filters.
 */
public final class RequestSpecBuilderUtil {

    private RequestSpecBuilderUtil() {
    }

    public static RequestSpecification getBaseRequestSpec() {
        ConfigManager config = ConfigManager.getInstance();
        return new RequestSpecBuilder()
                .setBaseUri(config.getBaseUrl())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
