package com.reanima.business.service.impl;

import com.reanima.business.repository.IngredientRepository;
import com.reanima.business.repository.model.Ingredient;
import com.reanima.business.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;
    private Ingredient ingredient;

    @Override
    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(int ingredientId) {
        return ingredientRepository.findById(ingredientId);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return ingredient;
    }

    @Override
    public List<Ingredient> deleteById(int ingredientId) {
        ingredientRepository.deleteById(ingredientId);
        return null;
    }
}
