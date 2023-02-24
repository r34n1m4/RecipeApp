package com.reanima.business.util;

public class LogMessages {

    //field validation
    public static final String FIELD_NOT_EMPTY = "This field should not be empty.";

    public static final String FIELD_NOT_NULL = "This field should not be null.";

    public static final String FIELD_NOT_BLANK = "This field should not be blank.";

    public static final String FIELD_MAX_LENGTH_50 = "This field should not exceed 50 characters.";

    public static final String FIELD_MAX_LENGTH_100 = "This field should not exceed 100 characters.";

    //exception messages customization
    public static final String ID_NOT_FOUND = "ID not found.";

    public static final String ID_FOUND = "ID found";

    public static final String VALIDATION_FAILED = "Validation failed.";

    public static final String RECIPE_WITH_ID_NOT_FOUND = "Recipe with this ID not found.";

    public static final String INGREDIENT_WITH_ID_NOT_FOUND = "Ingredient with this ID not found.";

    public static final String USER_WITH_ID_NOT_FOUND = "User with this ID not found.";

    public static final String RECIPE_WITH_NAME_ALREADY_EXIST = "Recipe with this name already exist.";

    public static final String INGREDIENT_WITH_THIS_NAME_ALREADY_EXIST = "Ingredient with this name already exist.";

    public static final String USER_WITH_THIS_EMAIL_ALREADY_EXIST = "User with this email already exist.";

    private LogMessages() {}
}
