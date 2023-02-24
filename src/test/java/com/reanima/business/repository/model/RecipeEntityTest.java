package com.reanima.business.repository.model;


import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.RecipeUtil.*;

class RecipeEntityTest {

    @Test
    void testGettersAndSetters() {
        RecipeEntity recipe = new RecipeEntity();
        recipe.setRecipeId(VALID_ID);
        recipe.setRecipeName(RECIPE_NAME);
        recipe.setRecipeDescription(RECIPE_DESCRIPTION);
        recipe.setRecipePreparation(RECIPE_PREPARATION);
        recipe.setCuisineType(CUISINE_TYPE);
        recipe.setDishType(DISH_TYPE);
        LocalDateTime now = LocalDateTime.now();
        recipe.setRecipeCreated(now);

        assertEquals(VALID_ID, recipe.getRecipeId());
        assertEquals(RECIPE_NAME, recipe.getRecipeName());
        assertEquals(RECIPE_DESCRIPTION, recipe.getRecipeDescription());
        assertEquals(RECIPE_PREPARATION, recipe.getRecipePreparation());
        assertEquals(CUISINE_TYPE, recipe.getCuisineType());
        assertEquals(DISH_TYPE, recipe.getDishType());
        assertEquals(now, recipe.getRecipeCreated());
    }

    @Test
    void testEqualsAndHashCode() {
        RecipeEntity recipe1 = new RecipeEntity();
        recipe1.setRecipeId(VALID_ID);
        RecipeEntity recipe2 = new RecipeEntity();
        recipe2.setRecipeId(VALID_ID);
        RecipeEntity recipe3 = new RecipeEntity();
        recipe3.setRecipeId(VALID_ID + 1);

        assertEquals(recipe1, recipe2);
        assertEquals(recipe1.hashCode(), recipe2.hashCode());
        assertNotEquals(recipe1, recipe3);
        assertNotEquals(recipe1.hashCode(), recipe3.hashCode());
    }

    @Test
    void testToString() {
        RecipeEntity recipe = new RecipeEntity();
        recipe.setRecipeId(VALID_ID);
        recipe.setRecipeName(RECIPE_NAME);
        recipe.setRecipeDescription(RECIPE_DESCRIPTION);
        recipe.setRecipePreparation(RECIPE_PREPARATION);
        recipe.setCuisineType(CUISINE_TYPE);
        recipe.setDishType(DISH_TYPE);
        LocalDateTime now = LocalDateTime.now();
        recipe.setRecipeCreated(now);

        String expected = "RecipeEntity(recipeId=1," +
                " recipeName=Chicken curry," +
                " recipeDescription=Spicy as hell," +
                " recipePreparation=Just do it," +
                " cuisineType=Any type," +
                " dishType=Any dish," +
                " recipeCreated=" +
                now + ")";
        assertEquals(expected, recipe.toString());
    }

    @Test
    void testAllArgsConstructor() {
        int recipeId = VALID_ID;
        String recipeName = RECIPE_NAME;
        String recipeDescription = RECIPE_DESCRIPTION;
        String recipePreparation = RECIPE_PREPARATION;
        String cuisineType = CUISINE_TYPE;
        String dishType = DISH_TYPE;
        LocalDateTime now = LocalDateTime.now();

        RecipeEntity recipe = new RecipeEntity(
                recipeId,
                recipeName,
                recipeDescription,
                recipePreparation,
                cuisineType,
                dishType,
                now);

        assertEquals(recipeId, recipe.getRecipeId());
        assertEquals(recipeName, recipe.getRecipeName());
        assertEquals(recipeDescription, recipe.getRecipeDescription());
        assertEquals(recipePreparation, recipe.getRecipePreparation());
        assertEquals(cuisineType, recipe.getCuisineType());
        assertEquals(dishType, recipe.getDishType());
        assertEquals(now, recipe.getRecipeCreated());
    }

    @Test
    void testBuilder() {
        LocalDateTime now = LocalDateTime.now();
        RecipeEntity recipe = RecipeEntity.builder()
                .recipeId(VALID_ID)
                .recipeName(RECIPE_NAME)
                .recipeDescription(RECIPE_DESCRIPTION)
                .recipePreparation(RECIPE_PREPARATION)
                .cuisineType(CUISINE_TYPE)
                .dishType(DISH_TYPE)
                .recipeCreated(now)
                .build();

        assertEquals(VALID_ID, recipe.getRecipeId());
        assertEquals(RECIPE_NAME, recipe.getRecipeName());
        assertEquals(RECIPE_DESCRIPTION, recipe.getRecipeDescription());
        assertEquals(RECIPE_PREPARATION, recipe.getRecipePreparation());
        assertEquals(CUISINE_TYPE, recipe.getCuisineType());
        assertEquals(DISH_TYPE, recipe.getDishType());
        assertEquals(now, recipe.getRecipeCreated());
    }

}
