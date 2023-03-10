package com.reanima.business.repository.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.reanima.util.IngredientUtil.INGREDIENT_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.CommonUtil.QUANTITY;
import static com.reanima.util.RecipeUtil.*;

@RunWith(MockitoJUnitRunner.class)
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
                localDateTime +
                ", recipeIngredients=[])";
        assertEquals(expected, recipeEntity.toString());
    }
    @Test
    public void testAllArgsConstructor2() {
        int recipeId = VALID_ID;
        String recipeName = "Test Recipe";
        String recipeDescription = "Test Recipe Description";
        String recipePreparation = "Test Recipe Preparation";
        String cuisineType = "Test Cuisine";
        String dishType = "Test Dish";
        LocalDateTime recipeCreated = LocalDateTime.now();

        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setIngredientId(VALID_ID);
        ingredientEntity.setIngredientName("Test Ingredient");

        RecipesIngredientsEntity recipesIngredientsEntity = new RecipesIngredientsEntity();
        recipesIngredientsEntity.setRecipeEntity(new RecipeEntity());
        recipesIngredientsEntity.setIngredientEntity(ingredientEntity);
        recipesIngredientsEntity.setQuantity(QUANTITY);

        Set<RecipesIngredientsEntity> recipeIngredients = new HashSet<>();
        recipeIngredients.add(recipesIngredientsEntity);

        RecipeEntity recipe = new RecipeEntity(recipeId, recipeName, recipeDescription, recipePreparation,
                cuisineType, dishType, recipeCreated, recipeIngredients);

        assertNotNull(recipe);
        assertEquals(recipeId, recipe.getRecipeId());
        assertEquals(recipeName, recipe.getRecipeName());
        assertEquals(recipeDescription, recipe.getRecipeDescription());
        assertEquals(recipePreparation, recipe.getRecipePreparation());
        assertEquals(cuisineType, recipe.getCuisineType());
        assertEquals(dishType, recipe.getDishType());
        assertEquals(recipeCreated, recipe.getRecipeCreated());
        assertEquals(recipeIngredients, recipe.getRecipeIngredients());
    }
    @Test
    void testAllArgsConstructor() {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setIngredientId(VALID_ID);
        ingredientEntity.setIngredientName(INGREDIENT_NAME);

        RecipesIngredientsEntity recipesIngredientsEntity = new RecipesIngredientsEntity();
        recipesIngredientsEntity.setRecipeEntity(new RecipeEntity());
        recipesIngredientsEntity.setIngredientEntity(ingredientEntity);
        recipesIngredientsEntity.setQuantity(QUANTITY);

        Set<RecipesIngredientsEntity> recipeIngredients = new HashSet<>();
        recipeIngredients.add(recipesIngredientsEntity);

        recipeEntity = new RecipeEntity(
                VALID_ID,
                RECIPE_NAME,
                RECIPE_DESCRIPTION,
                RECIPE_PREPARATION,
                CUISINE_TYPE,
                DISH_TYPE,
                localDateTime,
                recipeIngredients);

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

//    //FIX STACKOVERFLOW ERROR
//    @Test
//    void testAddIngredient() {
//        // Create a new ingredient entity
//        IngredientEntity ingredientEntity = new IngredientEntity();
//        ingredientEntity.setIngredientName("Chicken");
//        ingredientEntity.setIngredientDescription("Fresh chicken");
//        ingredientEntity.setIngredientType("Meat");
//
//        recipeEntity.addIngredient(ingredientEntity, 500f);
//
//        assertEquals(1, recipeEntity.getRecipeIngredients().size());
//
//        IngredientEntity addedIngredientEntity = recipeEntity.getRecipeIngredients()
//                .iterator().next().getIngredientEntity();
//
//        assertEquals(ingredientEntity.getIngredientName(), addedIngredientEntity.getIngredientName());
//        assertEquals(ingredientEntity.getIngredientDescription(), addedIngredientEntity.getIngredientDescription());
//        assertEquals(ingredientEntity.getIngredientType(), addedIngredientEntity.getIngredientType());
//
//    }
//    //FIX STACKOVERFLOW ERROR
//    @Test
//    void testRemoveIngredient() {
//        // Create a new ingredient entity
//        IngredientEntity ingredientEntity = new IngredientEntity();
//        ingredientEntity.setIngredientName("Chicken");
//        ingredientEntity.setIngredientDescription("Fresh chicken");
//        ingredientEntity.setIngredientType("Meat");
//
//        recipeEntity.addIngredient(ingredientEntity, 500f);
//        assertEquals(1, recipeEntity.getRecipeIngredients().size());
//        recipeEntity.removeIngredient(ingredientEntity);
//        assertTrue(recipeEntity.getRecipeIngredients().isEmpty());
//
//    }
}