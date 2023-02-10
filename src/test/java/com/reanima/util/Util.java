package com.reanima.util;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.repository.model.RecipeEntity;

import java.time.LocalDateTime;

public class Util {

    public static final int VALID_ID = 1;

    public static final int INVALID_ID = -1;

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();

    public static final String RECIPE_URL = "/api/recipe";

    public static final String RECIPE_NAME = "Chicken curry";

    public static final String RECIPE_DESCRIPTION = "Spicy as hell";

    public static final String RECIPE_PREPARATION = "Just do it";

    public static final String CUISINE_TYPE = "Any type";

    public static final String DISH_TYPE = "Any dish";

    public static RecipeEntity recipeEntity() {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeId(VALID_ID);
        recipeEntity.setRecipeName(RECIPE_NAME);
        recipeEntity.setRecipeDescription(RECIPE_DESCRIPTION);
        recipeEntity.setRecipePreparation(RECIPE_PREPARATION);
        recipeEntity.setCuisineType(CUISINE_TYPE);
        recipeEntity.setDishType(DISH_TYPE);
        return recipeEntity;
    }

    public static RecipeDto recipeResponse (RecipeEntity recipeEntity) {
        RecipeDto recipeResponse = new RecipeDto();
        recipeResponse.setRecipeId(recipeEntity.getRecipeId());
        recipeResponse.setRecipeName(recipeEntity.getRecipeName());
        recipeResponse.setRecipeDescription(recipeEntity.getRecipeDescription());
        recipeResponse.setRecipePreparation(recipeEntity.getRecipePreparation());
        recipeResponse.setCuisineType(recipeEntity.getCuisineType());
        recipeResponse.setDishType(recipeEntity.getDishType());
        return recipeResponse;
    }
}
