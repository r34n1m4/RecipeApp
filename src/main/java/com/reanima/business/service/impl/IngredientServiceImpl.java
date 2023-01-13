package com.reanima.business.service.impl;

import com.reanima.business.mapper.IngredientMapper;
import com.reanima.business.mapper.RecipeMapper;
import com.reanima.business.model.IngredientDto;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.repository.IngredientRepository;
import com.reanima.business.repository.RecipeRepository;
import com.reanima.business.repository.model.IngredientEntity;
import com.reanima.business.repository.model.RecipeEntity;
import com.reanima.business.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientMapper ingredientMapper;


    @Override
    public List<IngredientDto> findAllIngredients() {
        List<IngredientEntity> ingredientEntity = ingredientRepository.findAll();
        return ingredientEntity.stream().map(ingredientMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<IngredientDto> findIngredientById(int ingredientId) {
        Optional<IngredientEntity> ingredientEntity = ingredientRepository.findById(ingredientId);
        return ingredientEntity.map(ingredientMapper::entityToDto);
    }

    @Override
    public void saveIngredient(IngredientDto ingredientDto) {
        IngredientEntity ingredientEntity = ingredientRepository.save(ingredientMapper.dtoToEntity(ingredientDto));
        ingredientMapper.entityToDto(ingredientEntity);
    }

    @Override
    public void deleteIngredientById(int ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
