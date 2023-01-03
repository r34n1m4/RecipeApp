package com.reanima.business.service;

import com.reanima.business.repository.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    public List<Recipe> findAll();

    public Optional<Recipe> findById(int theId);

    public Recipe save(Recipe theRecipe);

    public List<Recipe> deleteById(int theId);
}
