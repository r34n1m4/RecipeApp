package com.reanima.business.service;

import com.reanima.business.model.RecipeDto;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
//    public List<RecipeEntity> findAll();
//
//    public Optional<RecipeEntity> findById(int recipeId);
//
//    public RecipeEntity save(RecipeEntity theRecipeEntity);
//
//    public void deleteById(int recipeId);

    List<RecipeDto> findAllRecipes();

    Optional<RecipeDto> findRecipeById(int recipeId);

    void saveRecipe(RecipeDto recipeDto);

    void deleteRecipeById(int recipeId);
}
