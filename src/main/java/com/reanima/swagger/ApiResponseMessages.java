package com.reanima.swagger;

public class ApiResponseMessages {

    public static final int OK_CODE = 200;
    public static final String OK_MESSAGE = "Success";

    public static final int CREATED_CODE = 201;
    public static final String CREATED_MESSAGE = "Created";

    public static final int NO_CONTENT_CODE = 204;
    public static final String NO_CONTENT_MESSAGE = "No content";

    public static final int BAD_REQUEST_CODE = 400;
    public static final String BAD_REQUEST_MESSAGE = "Bad request";

    public static final int UNAUTHORIZED_CODE = 401;
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized";

    public static final int UNAUTHENTICATED_CODE = 403;
    public static final String UNAUTHENTICATED_MESSAGE = "Unauthenticated";

    public static final int NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MESSAGE = "Not found";

    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";

    private ApiResponseMessages() {}

}
