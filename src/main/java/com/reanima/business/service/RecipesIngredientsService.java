package com.reanima.business.service;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.model.RecipesIngredientsDto;

import java.util.List;

public interface RecipesIngredientsService {

    List<RecipesIngredientsDto> getAllRecipesIngredients();

    RecipesIngredientsDto getRecipesIngredientsById(int recipesIngredientsId);

    RecipesIngredientsDto saveRecipesIngredients(RecipesIngredientsDto recipesIngredientsDto);

    void deleteRecipesIngredients(int recipesIngredientsId);

    List<RecipesIngredientsDto> findByRecipeDto(RecipeDto recipeDto);

    List<RecipesIngredientsDto> findByIngredientDto(IngredientDto ingredientDto);

    List<RecipesIngredientsDto> findByRecipeName(String recipeName);

}
