package com.reanima.business.service;

import com.reanima.business.model.RecipeDto;

import java.util.List;
import java.util.Optional;

public interface RecipeService {

    List<RecipeDto> findAllRecipes();

    Optional<RecipeDto> findRecipeById(int recipeId);

    RecipeDto saveRecipe(RecipeDto recipeDto);

    void deleteRecipeById(int recipeId);

    RecipeDto updateRecipe(RecipeDto recipeDto);
}
