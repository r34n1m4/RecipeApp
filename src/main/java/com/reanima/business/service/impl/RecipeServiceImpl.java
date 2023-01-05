package com.reanima.business.service.impl;

import com.reanima.business.repository.RecipeRepository;
import com.reanima.business.repository.model.Recipe;
import com.reanima.business.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    private Recipe recipe;

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> findById(int recipeId) {
        return recipeRepository.findById(recipeId);
    }

    @Override
    public Recipe save(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public List<Recipe> deleteById(int recipeId) {
        recipeRepository.deleteById(recipeId);
        return null;
    }
}
