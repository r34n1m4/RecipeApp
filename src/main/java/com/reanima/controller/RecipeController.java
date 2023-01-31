package com.reanima.controller;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.service.impl.RecipeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.reanima.swagger.ApiResponseMessages.*;
import static com.reanima.swagger.SwaggerTags.RECIPE_CONTROLLER_TAG_NAME;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api")
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
    @GetMapping({"/recipelist"})
    public ModelAndView getAllRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-list");
        modelAndView.addObject("recipeEntity", recipeServiceImpl.findAllRecipes());
        return modelAndView;
    }

    @ApiOperation(value = "Showing Save form for Recipe",
            notes = "Returns Form for adding new Recipe")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/saverecipe")
    public ModelAndView saveForm() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-form");
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
    @PostMapping("/saverecipe")
    public String saveRecipe(@ModelAttribute("recipeDto") RecipeDto recipeDto) {
        recipeServiceImpl.saveRecipe(recipeDto);
        return "redirect:/recipelist";
    }

    @ApiOperation(value = "Updates existing Recipe",
            notes = "Updates RecipeDto, redirecting to /recipelist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @PostMapping("/updaterecipe")
    public String updateRecipe(@RequestParam("recipeId") int recipeId, Model model) {
        Optional<RecipeDto> recipeDto = recipeServiceImpl.findRecipeById(recipeId);
        model.addAttribute("recipeDto", recipeDto);
        return "recipe/recipe-form";
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
    @PostMapping("/deleterecipe")
    public String deleteRecipe(@RequestParam("recipeId") int recipeId) {
        recipeServiceImpl.deleteRecipeById(recipeId);
        return "redirect:/recipelist";
    }
}
