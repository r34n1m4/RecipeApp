package com.reanima.business.repository;

import com.reanima.business.repository.model.IngredientEntity;
import com.reanima.business.repository.model.RecipeEntity;
import com.reanima.business.repository.model.RecipesIngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesIngredientsRepository
        extends JpaRepository<RecipesIngredientsEntity, Integer> {

    List<RecipesIngredientsEntity> findByRecipeEntity(RecipeEntity recipeEntity);

    List<RecipesIngredientsEntity> findByIngredientEntity(IngredientEntity ingredientEntity);

    List<RecipesIngredientsEntity> findByRecipeEntity_RecipeName(String recipeName);

}
