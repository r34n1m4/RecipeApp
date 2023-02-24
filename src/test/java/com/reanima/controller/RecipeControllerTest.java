package com.reanima.controller;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.service.impl.RecipeServiceImpl;
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
import static com.reanima.util.RecipeUtil.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    @Mock
    private RecipeServiceImpl recipeServiceImpl;

    @InjectMocks
    private RecipeController recipeController;

    private List<RecipeDto> recipeDtoList;

    @Mock
    private RecipeDto recipeDto;

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

        recipeDtoList = new ArrayList<>();
        RecipeDto testRecipe = new RecipeDto();
        testRecipe.setRecipeId(VALID_ID);
        testRecipe.setRecipeName(RECIPE_NAME);
        testRecipe.setRecipeDescription(RECIPE_DESCRIPTION);
        recipeDtoList.add(testRecipe);

    }

    @Test
    public void testFindAllRecipes() {
        when(recipeServiceImpl.findAllRecipes()).thenReturn(recipeDtoList);

        ModelAndView modelAndView = recipeController.findAllRecipes();

        assertEquals("recipe/recipe-list", modelAndView.getViewName());
        assertEquals(recipeDtoList, modelAndView.getModel().get("recipeEntity"));
    }

    @Test
    public void testSaveRecipeForm() {
        ModelAndView modelAndView = recipeController.saveRecipeForm();

        assertEquals("recipe/recipe-form-save", modelAndView.getViewName());
        RecipeDto recipeDto = (RecipeDto) modelAndView.getModel().get("recipeDto");
        assertEquals(new RecipeDto(), recipeDto);
    }

    @Test
    public void testSaveRecipe() {
        RecipeDto recipeDto = new RecipeDto();
        ResponseEntity<Void> responseEntity = recipeController.saveRecipe(recipeDto);

        verify(recipeServiceImpl).saveRecipe(recipeDto);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(RECIPE_URL + "/recipelist", Objects
                .requireNonNull(responseEntity
                        .getHeaders()
                        .getLocation())
                .getPath());
    }

    @Test
    public void testUpdateRecipe() {
        RecipeDto recipeDto = new RecipeDto();
        ResponseEntity<Void> responseEntity = recipeController.updateRecipe(recipeDto);

        verify(recipeServiceImpl).updateRecipe(recipeDto);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(RECIPE_URL + "/recipelist", Objects
                .requireNonNull(responseEntity
                        .getHeaders().
                        getLocation())
                .getPath());
    }

    @Test
    public void testUpdateRecipeForm() {
        when(recipeServiceImpl.findRecipeById(VALID_ID)).thenReturn(Optional.of(recipeDto));

        ModelMap model = new ModelMap();
        String viewName = recipeController.updateRecipeForm(VALID_ID, model);

        assertEquals("recipe/recipe-form-update", viewName);
        assertEquals(Optional.of(recipeDto), model.get("recipeDto"));

    }

    @Test
    public void testDeleteRecipe() {
        ResponseEntity<Void> responseEntity = recipeController.deleteRecipe(VALID_ID);

        verify(recipeServiceImpl).deleteRecipeById(VALID_ID);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(RECIPE_URL + "/recipelist", Objects
                .requireNonNull(responseEntity
                        .getHeaders()
                        .getLocation())
                .getPath());
    }

}
