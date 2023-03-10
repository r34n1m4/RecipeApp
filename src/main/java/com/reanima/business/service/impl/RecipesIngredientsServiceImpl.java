package com.reanima.business.service.impl;

import com.reanima.business.mapper.RecipesIngredientsMapper;
import com.reanima.business.model.IngredientDto;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.model.RecipesIngredientsDto;
import com.reanima.business.repository.RecipesIngredientsRepository;
import com.reanima.business.repository.model.IngredientEntity;
import com.reanima.business.repository.model.RecipeEntity;
import com.reanima.business.repository.model.RecipesIngredientsEntity;
import com.reanima.business.service.RecipesIngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipesIngredientsServiceImpl implements RecipesIngredientsService {

    @Autowired
    private RecipesIngredientsRepository recipesIngredientsRepository;

    @Autowired
    private RecipesIngredientsMapper recipesIngredientsMapper;

    @Autowired
    public RecipesIngredientsServiceImpl(RecipesIngredientsRepository recipesIngredientsRepository,
                                         RecipesIngredientsMapper recipesIngredientsMapper) {
        this.recipesIngredientsRepository = recipesIngredientsRepository;
        this.recipesIngredientsMapper = recipesIngredientsMapper;
    }

    @Override
    public List<RecipesIngredientsDto> getAllRecipesIngredients() {
        List<RecipesIngredientsEntity> entities = recipesIngredientsRepository.findAll();
        return entities.stream()
                .map(recipesIngredientsMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecipesIngredientsDto getRecipesIngredientsById(int recipesIngredientsId) {
        RecipesIngredientsEntity entity = recipesIngredientsRepository.findById(recipesIngredientsId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid recipe ingredient ID: " + recipesIngredientsId));
        return recipesIngredientsMapper.entityToDto(entity);
    }

    @Override
    public RecipesIngredientsDto saveRecipesIngredients(RecipesIngredientsDto recipesIngredientsDto) {

        RecipesIngredientsEntity savedEntity = recipesIngredientsRepository.save(recipesIngredientsMapper.dtoToEntity(recipesIngredientsDto));
        return recipesIngredientsMapper.entityToDto(savedEntity);
    }

    @Override
    public void deleteRecipesIngredients(int recipesIngredientsId) {
        recipesIngredientsRepository.deleteById(recipesIngredientsId);
    }

    @Override
    public List<RecipesIngredientsDto> findByRecipeDto(RecipeDto recipeDto) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setRecipeId(recipeDto.getRecipeId());
        recipeEntity.setRecipeName(recipeDto.getRecipeName());
        List<RecipesIngredientsEntity> entities = recipesIngredientsRepository.findByRecipeEntity(recipeEntity);
        return entities.stream()
                .map(recipesIngredientsMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipesIngredientsDto> findByIngredientDto(IngredientDto ingredientDto) {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setIngredientId(ingredientDto.getIngredientId());
        ingredientEntity.setIngredientName(ingredientDto.getIngredientName());
        List<RecipesIngredientsEntity> entities = recipesIngredientsRepository.findByIngredientEntity(ingredientEntity);
        return entities.stream()
                .map(recipesIngredientsMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipesIngredientsDto> findByRecipeName(String recipeName) {
        List<RecipesIngredientsEntity> entities = recipesIngredientsRepository.findByRecipeEntity_RecipeName(recipeName);
        return entities.stream()
                .map(recipesIngredientsMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
