package com.reanima.business.service;

import com.reanima.business.model.IngredientDto;

import java.util.List;
import java.util.Optional;

public interface IngredientService {

    List<IngredientDto> findAllIngredients();

    Optional<IngredientDto> findIngredientById(int ingredientId);

    void saveIngredient(IngredientDto ingredientDto);

    void deleteIngredientById(int ingredientId);
}
