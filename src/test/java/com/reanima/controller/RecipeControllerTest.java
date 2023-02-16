//package com.reanima.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.reanima.business.model.RecipeDto;
//import com.reanima.business.repository.model.RecipeEntity;
//import com.reanima.business.service.RecipeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static com.reanima.util.Util.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(RecipeController.class)
//class RecipeControllerTest {
//
//    @MockBean
//    private RecipeService recipeService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    private RecipeDto recipeDto;
//    private List<RecipeDto> recipeDtoList;
//    private Optional<RecipeDto> recipeDtoOptional;
//
//    @BeforeEach
//    void setUp() {
//
//        RecipeEntity recipeEntity = new RecipeEntity();
//        recipeEntity.setRecipeId(99);
//
//        recipeDto = new RecipeDto();
//        recipeDto.setRecipeId(VALID_ID);
//        recipeDto.setRecipeName(RECIPE_NAME);
//        recipeDto.setRecipeDescription(RECIPE_DESCRIPTION);
//        recipeDto.setRecipePreparation(RECIPE_PREPARATION);
//        recipeDto.setCuisineType(CUISINE_TYPE);
//        recipeDto.setDishType(DISH_TYPE);
//
//        recipeDtoList = new ArrayList<>(Arrays.asList(recipeDto));
//        recipeDtoOptional = Optional.of(recipeDto);
//
//    }
//
//    @Test
//    void findAllRecipes_HttpRequest() throws Exception{
//        when(recipeService.findAllRecipes()).thenReturn(recipeDtoList);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(RECIPE_URL + "/recipelist")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(recipeDto)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipeId").value(VALID_ID))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipe.id").value(99))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipeName").value(RECIPE_NAME))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipeDescription").value(RECIPE_DESCRIPTION))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].recipePreparation").value(RECIPE_PREPARATION))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cuisineType").value(CUISINE_TYPE))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dishType").value(DISH_TYPE))
//                .andReturn();
//        verify(recipeService, times(1)).findAllRecipes();
//    }
//
//    @Test
//    void saveRecipeForm() {
//    }
//
//    @Test
//    void saveRecipe() {
//    }
//
//    @Test
//    void updateRecipe() {
//    }
//
//    @Test
//    void updateRecipeForm() {
//    }
//
//    @Test
//    void deleteRecipe() {
//    }
//}