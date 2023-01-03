package com.reanima.business.repository;

import com.reanima.business.repository.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository
        extends JpaRepository<Recipe, Integer> {
    //ascending search by recipe name
    List<Recipe> findAllByOrderByRecipeNameAsc();

    //descending search by recipe name
    List<Recipe> findAllByOrderByRecipeNameDesc();
}
