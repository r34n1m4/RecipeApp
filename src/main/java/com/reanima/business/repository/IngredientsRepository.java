package com.reanima.business.repository;

import com.reanima.business.repository.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientsRepository
        extends JpaRepository<Ingredients, Integer> {
}
