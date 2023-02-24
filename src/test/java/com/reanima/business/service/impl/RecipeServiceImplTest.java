package com.reanima.business.service.impl;

import com.reanima.business.handler.exception.RecipeException;
import com.reanima.business.mapper.RecipeMapper;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.repository.RecipeRepository;
import com.reanima.business.repository.model.RecipeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.reanima.business.util.LogMessages.RECIPE_WITH_ID_NOT_FOUND;
import static com.reanima.util.CommonUtil.*;
import static com.reanima.util.RecipeUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeMapper recipeMapper;
    private RecipeEntity recipeEntity;
    private RecipeDto recipeDto;
    private List<RecipeEntity> recipeEntityList;

    @BeforeEach
    public void setUp() {

        recipeDto = new RecipeDto(
                VALID_ID,
                RECIPE_NAME,
                RECIPE_DESCRIPTION,
                RECIPE_PREPARATION,
                CUISINE_TYPE,
                DISH_TYPE,
                LOCAL_DATE_TIME
        );

        recipeEntity = new RecipeEntity(
                VALID_ID,
                RECIPE_NAME,
                RECIPE_DESCRIPTION,
                RECIPE_PREPARATION,
                CUISINE_TYPE,
                DISH_TYPE,
                LOCAL_DATE_TIME
        );

        recipeEntityList = new ArrayList<>(List.of(recipeEntity));
    }

    @Test
    @DisplayName("Test: Save Recipe")
    void testSaveRecipe() {
        when(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity);
        when(recipeMapper.entityToDto(recipeEntity)).thenReturn(recipeDto);
        when(recipeMapper.dtoToEntity(recipeDto)).thenReturn(recipeEntity);
        RecipeDto recipeSaved = recipeService.saveRecipe(recipeDto);
        assertFalse(recipeService.RecipeNameMatch(recipeSaved));
        assertEquals(recipeDto, recipeSaved);
        verify(recipeRepository, times(1)).save(recipeEntity);
    }

    @Test
    @DisplayName("Test: Save Recipe when already exist")
    void testSaveRecipe_WhenAlreadyExist() {
        when(recipeService.RecipeNameMatch(recipeDto))
                .thenThrow(new RecipeException("Recipe with this name already exist."));
        Assertions.assertThrows(RecipeException.class, () -> recipeService.RecipeNameMatch(recipeDto));
    }

    @Test
    @DisplayName("Test: Find all recipes")
    void testFindAllRecipes() {
        when(recipeRepository.findAll()).thenReturn(recipeEntityList);
        when(recipeMapper.entityToDto(recipeEntity)).thenReturn(recipeDto);
        List<RecipeDto> recipeDtoList = recipeService.findAllRecipes();
        assertEquals(1, recipeDtoList.size());
        assertEquals(1, recipeDtoList.get(0).getRecipeId());
        assertEquals(RECIPE_NAME, recipeDtoList.get(0).getRecipeName());
    }

    @Test
    @DisplayName("Test: Find Recipe by valid ID")
    void testFindRecipeBy_ValidId() throws NoSuchElementException {
        when(recipeRepository.findById(VALID_ID)).thenReturn(Optional.of(recipeEntity));
        when(recipeMapper.entityToDto(recipeEntity)).thenReturn(recipeDto);
        Optional<RecipeDto> returnedRecipe = recipeService.findRecipeById(recipeDto.getRecipeId());
        assertEquals(recipeDto.getRecipeId(), returnedRecipe.get().getRecipeId());
        assertEquals(recipeDto.getRecipeName(), returnedRecipe.get().getRecipeName());
        assertEquals(recipeDto.getRecipeDescription(), returnedRecipe.get().getRecipeDescription());
        assertEquals(recipeDto.getRecipePreparation(), returnedRecipe.get().getRecipePreparation());
        assertEquals(recipeDto.getCuisineType(), returnedRecipe.get().getCuisineType());
        assertEquals(recipeDto.getDishType(), returnedRecipe.get().getDishType());
        verify(recipeRepository, times(1)).findById(VALID_ID);
    }

    @Test
    @DisplayName("Test: Find Recipe by invalid ID")
    void testFindRecipeBy_InvalidId() {
        when(recipeRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
        assertFalse(recipeService.findRecipeById(INVALID_ID).isPresent());
        verify(recipeRepository, times(1)).findById(INVALID_ID);
    }

    @Test
    @DisplayName("Test: Delete Recipe by valid ID")
    void testDeleteRecipeBy_ValidId() {
        recipeService.deleteRecipeById(recipeDto.getRecipeId());
        verify(recipeRepository, times(1)).deleteById(recipeDto.getRecipeId());
    }

    @Test
    @DisplayName("Test: Delete Recipe by invalid ID")
    void testDeleteRecipeBy_InvalidId() {
        lenient().doThrow(new RecipeException(RECIPE_WITH_ID_NOT_FOUND))
                .when(recipeRepository).deleteById(INVALID_ID);
        Assertions.assertThrows(RecipeException.class,() -> recipeService.deleteRecipeById(INVALID_ID));
        verify(recipeRepository, times(1)).deleteById(INVALID_ID);
    }

    @Test
    @DisplayName("Test: Update Recipe")
    void testUpdateRecipe() {
        when(recipeRepository.save(recipeEntity)).thenReturn(recipeEntity);
        when(recipeMapper.entityToDto(recipeEntity)).thenReturn(recipeDto);
        when(recipeMapper.dtoToEntity(recipeDto)).thenReturn(recipeEntity);
        RecipeDto recipeUpdated = recipeService.saveRecipe(recipeDto);
        assertFalse(recipeService.RecipeNameMatch(recipeUpdated));
        assertEquals(recipeDto, recipeUpdated);
        verify(recipeRepository, times(1)).save(recipeEntity);
    }

    @Test
    @DisplayName("Test: Recipe name match when found")
    void testRecipeNameMatch_Found() {
        when(recipeRepository.findAll()).thenReturn(recipeEntityList);
        assertTrue(recipeService.RecipeNameMatch(recipeDto));
    }

    @Test
    @DisplayName("Test: Recipe name match when not found")
    void testRecipeNameMatch_NotFound() {
        recipeDto.setRecipeName("Random Name");
        when(recipeRepository.findAll()).thenReturn(recipeEntityList);
        assertFalse(recipeService.RecipeNameMatch(recipeDto));
    }
}