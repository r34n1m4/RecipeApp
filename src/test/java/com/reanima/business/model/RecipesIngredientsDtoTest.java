package com.reanima.business.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reanima.util.CommonUtil.QUANTITY;
import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.IngredientUtil.INGREDIENT_NAME;
import static com.reanima.util.RecipeUtil.RECIPE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class RecipesIngredientsDtoTest {

    public static final int recipesIngredientsId = VALID_ID;
    public static final int recipeId = VALID_ID;
    public static final String recipeName = RECIPE_NAME;
    public static final int ingredientId = VALID_ID;
    public static final String ingredientName = INGREDIENT_NAME;
    public static final float quantity = QUANTITY;

    private RecipesIngredientsDto recipesIngredientsDto;
    @BeforeEach
    public void setUp() {
        recipesIngredientsDto = new RecipesIngredientsDto();
    }

    @Test
    public void testBuilder() {

        recipesIngredientsDto = RecipesIngredientsDto.builder()
                .recipesIngredientsId(recipesIngredientsId)
                .recipeId(recipeId)
                .recipeName(recipeName)
                .ingredientId(ingredientId)
                .ingredientName(ingredientName)
                .quantity(quantity)
                .build();

        assertEquals(recipesIngredientsId, recipesIngredientsDto.getRecipesIngredientsId());
        assertEquals(recipeId, recipesIngredientsDto.getRecipeId());
        assertEquals(recipeName, recipesIngredientsDto.getRecipeName());
        assertEquals(ingredientId, recipesIngredientsDto.getIngredientId());
        assertEquals(ingredientName, recipesIngredientsDto.getIngredientName());
        assertEquals(quantity, recipesIngredientsDto.getQuantity());
    }

    @Test
    public void testSetterGetter() {

        recipesIngredientsDto.setRecipesIngredientsId(recipesIngredientsId);
        recipesIngredientsDto.setRecipeId(recipeId);
        recipesIngredientsDto.setRecipeName(recipeName);
        recipesIngredientsDto.setIngredientId(ingredientId);
        recipesIngredientsDto.setIngredientName(ingredientName);
        recipesIngredientsDto.setQuantity(quantity);

        assertEquals(recipesIngredientsId, recipesIngredientsDto.getRecipesIngredientsId());
        assertEquals(recipeId, recipesIngredientsDto.getRecipeId());
        assertEquals(recipeName, recipesIngredientsDto.getRecipeName());
        assertEquals(ingredientId, recipesIngredientsDto.getIngredientId());
        assertEquals(ingredientName, recipesIngredientsDto.getIngredientName());
        assertEquals(quantity, recipesIngredientsDto.getQuantity());
    }

    @Test
    public void testNoArgsConstructor() {

        assertEquals(0, recipesIngredientsDto.getRecipesIngredientsId());
        assertEquals(0, recipesIngredientsDto.getRecipeId());
        assertNull(recipesIngredientsDto.getRecipeName());
        assertEquals(0, recipesIngredientsDto.getIngredientId());
        assertNull(recipesIngredientsDto.getIngredientName());
        assertEquals(0.0f, recipesIngredientsDto.getQuantity());
    }

    @Test
    public void testAllArgsConstructor() {

        RecipesIngredientsDto dto =
                new RecipesIngredientsDto(recipesIngredientsId, recipeId, recipeName, ingredientId, ingredientName, quantity);

        assertEquals(recipesIngredientsId, dto.getRecipesIngredientsId());
        assertEquals(recipeId, dto.getRecipeId());
        assertEquals(recipeName, dto.getRecipeName());
        assertEquals(ingredientId, dto.getIngredientId());
        assertEquals(ingredientName, dto.getIngredientName());
        assertEquals(quantity, dto.getQuantity());
    }

}
