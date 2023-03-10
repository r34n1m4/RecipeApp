package com.reanima.business.service.impl;

import com.reanima.business.mapper.RecipesIngredientsMapper;
import com.reanima.business.model.IngredientDto;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.model.RecipesIngredientsDto;
import com.reanima.business.repository.RecipesIngredientsRepository;
import com.reanima.business.repository.model.IngredientEntity;
import com.reanima.business.repository.model.RecipeEntity;
import com.reanima.business.repository.model.RecipesIngredientsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.IngredientUtil.INGREDIENT_NAME;
import static com.reanima.util.RecipeUtil.RECIPE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class RecipesIngredientsServiceImplTest {

    @Mock
    private RecipesIngredientsRepository recipesIngredientsRepository;

    @Mock
    private RecipesIngredientsMapper recipesIngredientsMapper;

    @InjectMocks
    private RecipesIngredientsServiceImpl recipesIngredientsServiceImpl;

    private List<RecipesIngredientsEntity> recipesIngredientsEntityList;
    private List<RecipesIngredientsDto> recipesIngredientsDtoList;
    private RecipesIngredientsEntity recipesIngredientsEntity;
    private RecipesIngredientsDto recipesIngredientsDto;
    private RecipeDto recipeDto;
    private RecipeEntity recipeEntity;
    private IngredientDto ingredientDto;
    private IngredientEntity ingredientEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipesIngredientsEntityList = new ArrayList<>();
        recipesIngredientsDtoList = new ArrayList<>();
        recipesIngredientsEntity = new RecipesIngredientsEntity();
        recipesIngredientsDto = new RecipesIngredientsDto();
        recipeDto = new RecipeDto();
        recipeEntity = new RecipeEntity();
        ingredientDto = new IngredientDto();
        ingredientEntity = new IngredientEntity();
    }

    @Test
    @DisplayName("Test: Find all recipes ingredients")
    void testGetAllRecipesIngredients() {

        recipesIngredientsEntityList.add(new RecipesIngredientsEntity());
        recipesIngredientsEntityList.add(new RecipesIngredientsEntity());
        when(recipesIngredientsRepository.findAll()).thenReturn(recipesIngredientsEntityList);

        recipesIngredientsDtoList.add(new RecipesIngredientsDto());
        recipesIngredientsDtoList.add(new RecipesIngredientsDto());
        when(recipesIngredientsMapper.entityToDto(any(RecipesIngredientsEntity.class))).thenReturn(new RecipesIngredientsDto());

        List<RecipesIngredientsDto> result = recipesIngredientsServiceImpl.getAllRecipesIngredients();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Test: Find recipes ingredients by valid ID")
    void testGetRecipesIngredientsById() {

        recipesIngredientsEntity.setRecipesIngredientsId(VALID_ID);
        when(recipesIngredientsRepository.findById(VALID_ID)).thenReturn(Optional.of(recipesIngredientsEntity));

        recipesIngredientsDto.setRecipesIngredientsId(VALID_ID);
        when(recipesIngredientsMapper.entityToDto(recipesIngredientsEntity)).thenReturn(recipesIngredientsDto);

        RecipesIngredientsDto result = recipesIngredientsServiceImpl.getRecipesIngredientsById(VALID_ID);
        assertEquals(VALID_ID, result.getRecipesIngredientsId());
    }

    @Test
    @DisplayName("Test: Find recipes ingredients by invalid ID")
    void testGetRecipesIngredientsByIdWithInvalidId() {

        when(recipesIngredientsRepository.findById(VALID_ID)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> recipesIngredientsServiceImpl.getRecipesIngredientsById(VALID_ID));
    }

    @Test
    @DisplayName("Test: Save recipes ingredients")
    void testSaveRecipesIngredients() {

        when(recipesIngredientsMapper.dtoToEntity(recipesIngredientsDto)).thenReturn(recipesIngredientsEntity);

        RecipesIngredientsEntity savedEntity = new RecipesIngredientsEntity();
        savedEntity.setRecipesIngredientsId(VALID_ID);
        when(recipesIngredientsRepository.save(recipesIngredientsEntity)).thenReturn(savedEntity);

        RecipesIngredientsDto savedDto = new RecipesIngredientsDto();
        savedDto.setRecipesIngredientsId(VALID_ID);
        when(recipesIngredientsMapper.entityToDto(savedEntity)).thenReturn(savedDto);

        RecipesIngredientsDto result = recipesIngredientsServiceImpl.saveRecipesIngredients(recipesIngredientsDto);
        assertEquals(1, result.getRecipesIngredientsId());
    }


    @Test
    @DisplayName("Test: Delete recipes ingredients by valid ID")
    public void deleteRecipesIngredients() {

        recipesIngredientsServiceImpl.deleteRecipesIngredients(VALID_ID);
        verify(recipesIngredientsRepository).deleteById(VALID_ID);
    }

    @Test
    @DisplayName("Test: Find in recipes ingredients by recipe DTO with valid ID")
    public void findByRecipeDto() {

        recipeDto.setRecipeId(VALID_ID);
        recipeDto.setRecipeName(RECIPE_NAME);

        recipeEntity.setRecipeId(VALID_ID);
        recipeEntity.setRecipeName(RECIPE_NAME);

        recipesIngredientsEntityList.add(new RecipesIngredientsEntity());
        when(recipesIngredientsRepository.findByRecipeEntity(recipeEntity)).thenReturn(recipesIngredientsEntityList);
        when(recipesIngredientsMapper.entityToDto(any(RecipesIngredientsEntity.class))).thenReturn(new RecipesIngredientsDto());

        recipesIngredientsDtoList = recipesIngredientsServiceImpl.findByRecipeDto(recipeDto);
        verify(recipesIngredientsRepository).findByRecipeEntity(recipeEntity);
        verify(recipesIngredientsMapper, times(recipesIngredientsEntityList.size())).entityToDto(any(RecipesIngredientsEntity.class));
        assertEquals(recipesIngredientsEntityList.size(), recipesIngredientsDtoList.size());
    }

    @Test
    @DisplayName("Test: Find in recipes ingredients by ingredient DTO with valid ID")
    public void findByIngredientDto() {

        ingredientDto.setIngredientId(VALID_ID);
        ingredientDto.setIngredientName(INGREDIENT_NAME);

        ingredientEntity.setIngredientId(VALID_ID);
        ingredientEntity.setIngredientName(INGREDIENT_NAME);

        recipesIngredientsEntityList.add(new RecipesIngredientsEntity());
        when(recipesIngredientsRepository.findByIngredientEntity(ingredientEntity)).thenReturn(recipesIngredientsEntityList);
        when(recipesIngredientsMapper.entityToDto(any(RecipesIngredientsEntity.class))).thenReturn(new RecipesIngredientsDto());

        recipesIngredientsDtoList = recipesIngredientsServiceImpl.findByIngredientDto(ingredientDto);
        verify(recipesIngredientsRepository).findByIngredientEntity(ingredientEntity);
        verify(recipesIngredientsMapper, times(recipesIngredientsEntityList.size())).entityToDto(any(RecipesIngredientsEntity.class));
        assertEquals(recipesIngredientsEntityList.size(), recipesIngredientsDtoList.size());
    }

    @Test
    @DisplayName("Test: Find in recipes ingredients by recipe Name")
    public void findByRecipeName() {

        recipesIngredientsEntityList.add(new RecipesIngredientsEntity());
        when(recipesIngredientsRepository.findByRecipeEntity_RecipeName(RECIPE_NAME)).thenReturn(recipesIngredientsEntityList);
        when(recipesIngredientsMapper.entityToDto(any(RecipesIngredientsEntity.class))).thenReturn(new RecipesIngredientsDto());

        recipesIngredientsDtoList = recipesIngredientsServiceImpl.findByRecipeName(RECIPE_NAME);
        verify(recipesIngredientsRepository).findByRecipeEntity_RecipeName(RECIPE_NAME);
        verify(recipesIngredientsMapper, times(recipesIngredientsEntityList.size())).entityToDto(any(RecipesIngredientsEntity.class));
        assertEquals(recipesIngredientsEntityList.size(), recipesIngredientsDtoList.size());
    }
}