package com.uam.automation.api.utils;

public class Headers {

    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";
    public static final String APPLICATION_JSON = "application/json";

    public static final String TOKEN = "token";

    private Headers() {
        throw new IllegalStateException("Utility class");
    }
}
