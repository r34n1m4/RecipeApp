package com.reanima.swagger;

import springfox.documentation.service.Contact;

public class SwaggerTags {

    public static final String INGREDIENT_CONTROLLER_TAG_NAME = "Ingredient Controller";

    public static final String INGREDIENT_CONTROLLER_TAG_DESCRIPTION = "Provides CRUD functionality for Ingredient";

    public static final String RECIPE_CONTROLLER_TAG_NAME = "Recipe Controller";

    public static final String RECIPE_CONTROLLER_TAG_DESCRIPTION = "Provides CRUD functionality for Recipe";

    public static final String LOGIN_CONTROLLER_TAG_NAME = "Login Controller";

    public static final String LOGIN_CONTROLLER_TAG_DESCRIPTION = "Provides CRUD functionality for Login";

    public static final String TITLE = "Recipe App API";

    public static final String VERSION = "1.0";

    public static final String DESCRIPTION = "Private project: cooking recipe book management system";

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Vadim Zuravskis",
            "https://foodiecorner.azurewebsites.net",
            "vadimzuravskis@gmail.com");

    private SwaggerTags() {}
}
