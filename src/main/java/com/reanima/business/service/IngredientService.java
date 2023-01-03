package com.reanima.business.service;

import com.reanima.business.repository.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    public List<Ingredient> findAll();

    public Optional<Ingredient> findById(int theId);

    public Ingredient save(Ingredient ingredient);

    public List<Ingredient> deleteById(int theId);
}
