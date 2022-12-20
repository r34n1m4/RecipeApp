package com.reanima.business.repository;

import com.reanima.business.repository.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepo
        extends JpaRepository<Recipe, Integer> {
}
