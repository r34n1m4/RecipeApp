package com.reanima.business.repository;

import com.reanima.business.repository.model.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository
        extends JpaRepository<RecipeEntity, Integer> {

    List<RecipeEntity> findAllByOrderByRecipeNameAsc();

    List<RecipeEntity> findAllByOrderByRecipeNameDesc();

    List<RecipeEntity> findByRecipeName(String recipeName);
}
