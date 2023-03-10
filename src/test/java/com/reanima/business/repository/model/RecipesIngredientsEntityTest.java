package com.reanima.business.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reanima.util.CommonUtil.QUANTITY;
import static com.reanima.util.CommonUtil.VALID_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RecipesIngredientsEntityTest {

    private RecipeEntity recipeEntity;
    private IngredientEntity ingredientEntity;
    private RecipesIngredientsEntity recipesIngredientsEntity;

    @BeforeEach
    public void setUp() {
        recipeEntity = new RecipeEntity();
        ingredientEntity = new IngredientEntity();
        recipesIngredientsEntity = new RecipesIngredientsEntity(recipeEntity, ingredientEntity, QUANTITY);
    }

    @Test
    public void testGetRecipesIngredientsId() {
        int expected = 0;
        int actual = recipesIngredientsEntity.getRecipesIngredientsId();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRecipeEntity() {
        assertNotNull(recipesIngredientsEntity.getRecipeEntity());
    }

    @Test
    public void testGetIngredientEntity() {
        assertNotNull(recipesIngredientsEntity.getIngredientEntity());
    }

    @Test
    public void testGetQuantity() {
        float actual = recipesIngredientsEntity.getQuantity();
        assertEquals(QUANTITY, actual);
    }

    @Test
    public void testSetQuantity() {
        recipesIngredientsEntity.setQuantity(QUANTITY);
        float actual = recipesIngredientsEntity.getQuantity();
        assertEquals(QUANTITY, actual);
    }

    @Test
    public void testNoArgsConstructor() {
        RecipesIngredientsEntity emptyRecipesIngredientsEntity = new RecipesIngredientsEntity();
        assertNotNull(emptyRecipesIngredientsEntity);
    }

    @Test
    public void testAllArgsConstructor() {
        RecipesIngredientsEntity allArgsConstructorRecipesIngredientsEntity =
                new RecipesIngredientsEntity(VALID_ID, recipeEntity, ingredientEntity, QUANTITY);
        assertEquals(VALID_ID, allArgsConstructorRecipesIngredientsEntity.getRecipesIngredientsId());
    }

    @Test
    public void testBuilder() {
        RecipesIngredientsEntity builderRecipesIngredientsEntity = RecipesIngredientsEntity.builder()
                .recipeEntity(recipeEntity)
                .ingredientEntity(ingredientEntity)
                .quantity(QUANTITY)
                .build();

        assertEquals(recipeEntity, builderRecipesIngredientsEntity.getRecipeEntity());
        assertEquals(ingredientEntity, builderRecipesIngredientsEntity.getIngredientEntity());
        assertEquals(QUANTITY, builderRecipesIngredientsEntity.getQuantity());
    }
}
