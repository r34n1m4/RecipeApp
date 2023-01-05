package com.reanima.business.repository;

import com.reanima.business.repository.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository
        extends JpaRepository<Ingredient, Integer> {
    //ascending search by ingredients name
    List<Ingredient> findAllByOrderByIngredientNameAsc();

    //descending search by ingredients name
    List<Ingredient> findAllByOrderByIngredientNameDesc();
}
