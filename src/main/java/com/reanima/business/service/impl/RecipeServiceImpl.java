package com.reanima.business.service.impl;

import com.reanima.business.handler.exception.RecipeException;
import com.reanima.business.mapper.RecipeMapper;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.repository.RecipeRepository;
import com.reanima.business.repository.model.RecipeEntity;
import com.reanima.business.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.reanima.business.util.LogMessages.RECIPE_WITH_NAME_ALREADY_EXIST;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeMapper recipeMapper;


    @Override
    public List<RecipeDto> findAllRecipes() {
        List<RecipeEntity> recipeEntity = recipeRepository.findAll();
        return recipeEntity.stream().map(recipeMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<RecipeDto> findRecipeById(int recipeId) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(recipeId);
        return recipeEntity.map(recipeMapper::entityToDto);
    }

    @Override
    public RecipeDto saveRecipe(RecipeDto recipeDto) throws RecipeException{
        if (RecipeNameMatch(recipeDto)) {
            throw new RecipeException(RECIPE_WITH_NAME_ALREADY_EXIST);
        }
        RecipeEntity recipeEntity = recipeRepository.save(recipeMapper.dtoToEntity(recipeDto));
        return recipeMapper.entityToDto(recipeEntity);
    }

    @Override
    public void updateRecipe(RecipeDto recipeDto) {
        RecipeEntity recipeEntity = recipeRepository.save(recipeMapper.dtoToEntity(recipeDto));
        recipeMapper.entityToDto(recipeEntity);
    }

    @Override
    public void deleteRecipeById(int recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    public boolean RecipeNameMatch(RecipeDto recipeDto) {
        return recipeRepository.findAll().stream()
                .anyMatch(e -> e.getRecipeName()
                        .equalsIgnoreCase(recipeDto
                                .getRecipeName()));
    }
}
