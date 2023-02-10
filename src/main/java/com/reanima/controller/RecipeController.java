package com.reanima.controller;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.service.impl.RecipeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.reanima.swagger.ApiResponseMessages.*;
import static com.reanima.swagger.SwaggerTags.RECIPE_CONTROLLER_TAG_NAME;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/recipe")
@Api(tags = RECIPE_CONTROLLER_TAG_NAME)
public class RecipeController {

    @Autowired
    private RecipeServiceImpl recipeServiceImpl;

    @ApiOperation(value = "Find all Recipes",
            notes = "Returns List of Recipes bound to html page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/recipelist", method = RequestMethod.GET)
    public ModelAndView getAllRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-list");
        modelAndView.addObject("recipeEntity", recipeServiceImpl.findAllRecipes());
        return modelAndView;
    }

    @ApiOperation(value = "Save Recipe Form",
            notes = "Returns Form for adding new Recipe")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/saverecipe", method = RequestMethod.GET)
    public ModelAndView saveRecipeForm() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-form-save");
        RecipeDto recipeDto = new RecipeDto();
        modelAndView.addObject("recipeDto", recipeDto);
        return modelAndView;
    }

    @ApiOperation(value = "Save Recipe",
            notes = "Saving RecipeDto, redirecting to /recipelist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/saverecipe", method = RequestMethod.POST)
    public ResponseEntity<Void> saveRecipe(@ModelAttribute("recipeDto") RecipeDto recipeDto) {
        recipeServiceImpl.saveRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/recipe/recipelist")
                .build();
    }

    @ApiOperation(value = "Update Recipe",
            notes = "Updating RecipeDto, redirecting to /recipelist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/updaterecipe", method = RequestMethod.POST)
    public ResponseEntity<Void> updateRecipe(@ModelAttribute("recipeDto") RecipeDto recipeDto) {
        recipeServiceImpl.updateRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/recipe/recipelist")
                .build();
    }

    @ApiOperation(value = "Update Recipe Form",
            notes = "Updates RecipeDto, redirecting to /recipelist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/updaterecipeform", method = RequestMethod.POST)
    public String updateRecipeForm(@RequestParam("recipeId") int recipeId, Model model) {
        Optional<RecipeDto> recipeDto = recipeServiceImpl.findRecipeById(recipeId);
        model.addAttribute("recipeDto", recipeDto);
        return "recipe/recipe-form-update";
    }

    @ApiOperation(value = "Delete Recipe",
            notes = "Deletes RecipeDto, redirecting to /recipelist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/deleterecipe", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteRecipe(@RequestParam("recipeId") int recipeId) {
        recipeServiceImpl.deleteRecipeById(recipeId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/recipe/recipelist")
                .build();
    }
}
