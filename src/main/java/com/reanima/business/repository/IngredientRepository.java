package com.reanima.business.repository;

import com.reanima.business.repository.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository
        extends JpaRepository<Ingredient, Integer> {
    //ascending search by ingredients name
    List<Ingredient> findAllByOrderByIngredientNameAsc();

    //descending search by ingredients name
    List<Ingredient> findAllByOrderByIngredientNameDesc();
}
