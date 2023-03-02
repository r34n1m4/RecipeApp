package com.reanima.business.repository.model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.RecipeUtil.*;

class RecipeEntityTest {

    private RecipeEntity recipeEntity;
    private LocalDateTime localDateTime;

    @BeforeEach
    public void setUp() {
        recipeEntity = new RecipeEntity();
        localDateTime = LocalDateTime.now();
    }

    @Test
    void testGettersAndSetters() {

        recipeEntity.setRecipeId(VALID_ID);
        recipeEntity.setRecipeName(RECIPE_NAME);
        recipeEntity.setRecipeDescription(RECIPE_DESCRIPTION);
        recipeEntity.setRecipePreparation(RECIPE_PREPARATION);
        recipeEntity.setCuisineType(CUISINE_TYPE);
        recipeEntity.setDishType(DISH_TYPE);
        recipeEntity.setRecipeCreated(localDateTime);

        assertEquals(VALID_ID, recipeEntity.getRecipeId());
        assertEquals(RECIPE_NAME, recipeEntity.getRecipeName());
        assertEquals(RECIPE_DESCRIPTION, recipeEntity.getRecipeDescription());
        assertEquals(RECIPE_PREPARATION, recipeEntity.getRecipePreparation());
        assertEquals(CUISINE_TYPE, recipeEntity.getCuisineType());
        assertEquals(DISH_TYPE, recipeEntity.getDishType());
        assertEquals(localDateTime, recipeEntity.getRecipeCreated());
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
        recipeEntity.setRecipeId(VALID_ID);
        recipeEntity.setRecipeName(RECIPE_NAME);
        recipeEntity.setRecipeDescription(RECIPE_DESCRIPTION);
        recipeEntity.setRecipePreparation(RECIPE_PREPARATION);
        recipeEntity.setCuisineType(CUISINE_TYPE);
        recipeEntity.setDishType(DISH_TYPE);
        recipeEntity.setRecipeCreated(localDateTime);

        String expected = "RecipeEntity(recipeId=1," +
                " recipeName=Chicken curry," +
                " recipeDescription=Spicy as hell," +
                " recipePreparation=Just do it," +
                " cuisineType=Any type," +
                " dishType=Any dish," +
                " recipeCreated=" +
                localDateTime + ")";
        assertEquals(expected, recipeEntity.toString());
    }

    @Test
    void testAllArgsConstructor() {

        recipeEntity = new RecipeEntity(
                VALID_ID,
                RECIPE_NAME,
                RECIPE_DESCRIPTION,
                RECIPE_PREPARATION,
                CUISINE_TYPE,
                DISH_TYPE,
                localDateTime);

        assertEquals(VALID_ID, recipeEntity.getRecipeId());
        assertEquals(RECIPE_NAME, recipeEntity.getRecipeName());
        assertEquals(RECIPE_DESCRIPTION, recipeEntity.getRecipeDescription());
        assertEquals(RECIPE_PREPARATION, recipeEntity.getRecipePreparation());
        assertEquals(CUISINE_TYPE, recipeEntity.getCuisineType());
        assertEquals(DISH_TYPE, recipeEntity.getDishType());
        assertEquals(localDateTime, recipeEntity.getRecipeCreated());
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(recipeEntity);
    }

    @Test
    void testBuilder() {
        recipeEntity = RecipeEntity.builder()
                .recipeId(VALID_ID)
                .recipeName(RECIPE_NAME)
                .recipeDescription(RECIPE_DESCRIPTION)
                .recipePreparation(RECIPE_PREPARATION)
                .cuisineType(CUISINE_TYPE)
                .dishType(DISH_TYPE)
                .recipeCreated(localDateTime)
                .build();

        assertEquals(VALID_ID, recipeEntity.getRecipeId());
        assertEquals(RECIPE_NAME, recipeEntity.getRecipeName());
        assertEquals(RECIPE_DESCRIPTION, recipeEntity.getRecipeDescription());
        assertEquals(RECIPE_PREPARATION, recipeEntity.getRecipePreparation());
        assertEquals(CUISINE_TYPE, recipeEntity.getCuisineType());
        assertEquals(DISH_TYPE, recipeEntity.getDishType());
        assertEquals(localDateTime, recipeEntity.getRecipeCreated());
    }

}
