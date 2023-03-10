package com.reanima.business.service;

import com.reanima.business.model.RecipeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RecipeService {

    List<RecipeDto> findAllRecipes();

    Optional<RecipeDto> findRecipeById(int recipeId);

    RecipeDto saveRecipe(RecipeDto recipeDto);

    void deleteRecipeById(int recipeId);

    void updateRecipe(RecipeDto recipeDto);
}
