package com.reanima.controller;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.model.RecipesIngredientsDto;
import com.reanima.business.service.impl.RecipesIngredientsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.RecipeUtil.RECIPE_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecipesIngredientsControllerTest {

    @Mock
    private RecipesIngredientsServiceImpl recipesIngredientsServiceImpl;

    private RecipesIngredientsController controller;
    private List<RecipesIngredientsDto> recipesIngredientsDtoList;
    private RecipesIngredientsDto recipesIngredientsDto = new RecipesIngredientsDto();
    private RecipeDto recipeDto;
    private IngredientDto ingredientDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RecipesIngredientsController();
        controller.recipesIngredientsServiceImpl = recipesIngredientsServiceImpl;
        recipesIngredientsDtoList = new ArrayList<>();
        recipesIngredientsDto = new RecipesIngredientsDto();
        recipeDto = new RecipeDto();
        ingredientDto = new IngredientDto();
    }

    @Test
    public void testGetAllRecipesIngredients() {

        when(recipesIngredientsServiceImpl.getAllRecipesIngredients()).thenReturn(recipesIngredientsDtoList);

        ModelAndView result = controller.getAllRecipesIngredients();

        assertEquals("recipesIngredients", result.getViewName());
        assertEquals(recipesIngredientsDtoList, result.getModel().get("recipesIngredients"));
        verify(recipesIngredientsServiceImpl, times(1)).getAllRecipesIngredients();
    }

    @Test
    public void testGetRecipesIngredientsById() {

        when(recipesIngredientsServiceImpl.getRecipesIngredientsById(VALID_ID)).thenReturn(recipesIngredientsDto);
        ModelAndView result = controller.getRecipesIngredientsById(VALID_ID);

        assertEquals("recipeIngredient", result.getViewName());
        assertEquals(recipesIngredientsDto, result.getModel().get("recipesIngredients"));
        verify(recipesIngredientsServiceImpl, times(1)).getRecipesIngredientsById(VALID_ID);
    }

    @Test
    public void testSaveRecipesIngredients() {

        when(recipesIngredientsServiceImpl.saveRecipesIngredients(recipesIngredientsDto)).thenReturn(recipesIngredientsDto);
        ModelAndView result = controller.saveRecipesIngredients(recipesIngredientsDto);

        assertEquals("redirect:/recipes-ingredients/all", result.getViewName());
        verify(recipesIngredientsServiceImpl, times(1)).saveRecipesIngredients(recipesIngredientsDto);
    }

    @Test
    public void testDeleteRecipesIngredients() {

        ModelAndView result = controller.deleteRecipesIngredients(VALID_ID);

        assertEquals("redirect:/recipes-ingredients/all", result.getViewName());
        verify(recipesIngredientsServiceImpl, times(1)).deleteRecipesIngredients(VALID_ID);
    }

    @Test
    public void testFindByRecipeDto() {

        when(recipesIngredientsServiceImpl.findByRecipeDto(recipeDto)).thenReturn(recipesIngredientsDtoList);
        ModelAndView result = controller.findByRecipeDto(recipeDto);

        assertEquals("recipesIngredients", result.getViewName());
        assertEquals(recipesIngredientsDtoList, result.getModel().get("recipesIngredients"));
        verify(recipesIngredientsServiceImpl, times(1)).findByRecipeDto(recipeDto);
    }

    @Test
    public void testFindByIngredientDto() {

        when(recipesIngredientsServiceImpl.findByIngredientDto(ingredientDto)).thenReturn(recipesIngredientsDtoList);
        ModelAndView result = controller.findByIngredientDto(ingredientDto);

        assertEquals("recipesIngredients", result.getViewName());
        assertEquals(recipesIngredientsDtoList, result.getModel().get("recipesIngredients"));
        verify(recipesIngredientsServiceImpl, times(1)).findByIngredientDto(ingredientDto);
    }

    @Test
    public void testFindByRecipeName() {

        List<RecipesIngredientsDto> expectedRecipesIngredients = Collections.singletonList(new RecipesIngredientsDto());

        when(recipesIngredientsServiceImpl.findByRecipeName(RECIPE_NAME)).thenReturn(expectedRecipesIngredients);

        ModelAndView modelAndView = controller.findByRecipeName(RECIPE_NAME);

        assertEquals("recipesIngredients", modelAndView.getViewName());
        List<RecipesIngredientsDto> actualRecipesIngredients = (List<RecipesIngredientsDto>) modelAndView.getModel().get("recipesIngredients");
        assertEquals(expectedRecipesIngredients, actualRecipesIngredients);

        verify(recipesIngredientsServiceImpl).findByRecipeName(RECIPE_NAME);
    }

}