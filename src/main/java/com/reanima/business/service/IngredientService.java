package com.reanima.business.service;

import com.reanima.business.model.IngredientDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IngredientService {

    List<IngredientDto> findAllIngredients();

    Optional<IngredientDto> findIngredientById(int ingredientId);

    IngredientDto saveIngredient(IngredientDto ingredientDto);

    void deleteIngredientById(int ingredientId);

    void updateIngredient(IngredientDto ingredientDto);
}
