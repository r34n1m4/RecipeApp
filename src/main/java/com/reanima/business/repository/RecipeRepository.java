package com.reanima.business.repository;

import com.reanima.business.repository.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository
        extends JpaRepository<Recipe, Integer> {
    //ascending search by recipe name
    List<Recipe> findAllByOrderByRecipeNameAsc();

    //descending search by recipe name
    List<Recipe> findAllByOrderByRecipeNameDesc();
}
