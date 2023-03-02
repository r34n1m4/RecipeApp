package com.reanima.controller;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.service.impl.IngredientServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.reanima.util.CommonUtil.LOCAL_DATE_TIME;
import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.IngredientUtil.*;
import static com.reanima.util.RecipeUtil.RECIPE_DESCRIPTION;
import static com.reanima.util.RecipeUtil.RECIPE_NAME;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {

    @Mock
    private IngredientServiceImpl ingredientServiceImpl;

    @InjectMocks
    private IngredientController ingredientController;

    private List<IngredientDto> ingredientDtoList;

    @Mock
    private IngredientDto ingredientDto;

    @BeforeEach
    public void setUp() {

        ingredientDto = new IngredientDto(
                VALID_ID,
                INGREDIENT_NAME,
                INGREDIENT_DESCRIPTION,
                INGREDIENT_TYPE,
                CALORIES,
                CARBOHYDRATES,
                FAT,
                PROTEIN,
                WATER,
                CHOLESTEROL,
                LOCAL_DATE_TIME
        );

        ingredientDtoList = new ArrayList<>();
        IngredientDto testIngredient = new IngredientDto();
        testIngredient.setIngredientId(VALID_ID);
        testIngredient.setIngredientName(RECIPE_NAME);
        testIngredient.setIngredientDescription(RECIPE_DESCRIPTION);
        ingredientDtoList.add(testIngredient);

    }

    @Test
    public void testFindAllIngredients() {
        when(ingredientServiceImpl.findAllIngredients()).thenReturn(ingredientDtoList);

        ModelAndView modelAndView = ingredientController.findAllIngredients();

        assertEquals("ingredient/ingredient-list", modelAndView.getViewName());
        assertEquals(ingredientDtoList, modelAndView.getModel().get("ingredientEntity"));
    }

    @Test
    public void testSaveIngredientForm() {
        ModelAndView modelAndView = ingredientController.saveIngredientForm();

        assertEquals("ingredient/ingredient-form-save", modelAndView.getViewName());
        IngredientDto ingredientDto = (IngredientDto) modelAndView.getModel().get("ingredientDto");
        assertEquals(new IngredientDto(), ingredientDto);
    }

    @Test
    public void testSaveIngredient() {
        IngredientDto ingredientDto = new IngredientDto();
        ResponseEntity<Void> responseEntity = ingredientController.saveIngredient(ingredientDto);

        verify(ingredientServiceImpl).saveIngredient(ingredientDto);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(INGREDIENT_URL + "/ingredientlist", Objects
                .requireNonNull(responseEntity
                        .getHeaders()
                        .getLocation())
                .getPath());
    }

    @Test
    public void testUpdateIngredient() {
        IngredientDto ingredientDto = new IngredientDto();
        ResponseEntity<Void> responseEntity = ingredientController.updateIngredient(ingredientDto);

        verify(ingredientServiceImpl).updateIngredient(ingredientDto);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(INGREDIENT_URL + "/ingredientlist", Objects
                .requireNonNull(responseEntity
                        .getHeaders().
                        getLocation())
                .getPath());
    }

    @Test
    public void testUpdateIngredientForm() {
        when(ingredientServiceImpl.findIngredientById(VALID_ID)).thenReturn(Optional.of(ingredientDto));

        ModelMap model = new ModelMap();
        String viewName = ingredientController.updateIngredientForm(VALID_ID, model);

        assertEquals("ingredient/ingredient-form-update", viewName);
        assertEquals(Optional.of(ingredientDto), model.get("ingredientDto"));

    }

    @Test
    public void testDeleteIngredient() {
        ResponseEntity<Void> responseEntity = ingredientController.deleteIngredient(VALID_ID);

        verify(ingredientServiceImpl).deleteIngredientById(VALID_ID);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(INGREDIENT_URL + "/ingredientlist", Objects
                .requireNonNull(responseEntity
                        .getHeaders()
                        .getLocation())
                .getPath());
    }

}
