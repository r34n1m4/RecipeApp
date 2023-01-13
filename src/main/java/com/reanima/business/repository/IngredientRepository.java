package com.reanima.business.repository;

import com.reanima.business.repository.model.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository
        extends JpaRepository<IngredientEntity, Integer> {
    //ascending search by ingredients name
    List<IngredientEntity> findAllByOrderByIngredientNameAsc();

    //descending search by ingredients name
    List<IngredientEntity> findAllByOrderByIngredientNameDesc();
}
