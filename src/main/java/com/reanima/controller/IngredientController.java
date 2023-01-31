package com.reanima.controller;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.service.impl.IngredientServiceImpl;
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
import static com.reanima.swagger.SwaggerTags.INGREDIENT_CONTROLLER_TAG_NAME;
import static org.springframework.http.HttpStatus.OK;


@Controller
@RequestMapping("/api")
@Api(tags = INGREDIENT_CONTROLLER_TAG_NAME)
public class IngredientController {

    @Autowired
    private IngredientServiceImpl ingredientsServiceImpl;

    @ApiOperation(value = "Find all Ingredients",
            notes = "Returns List of Ingredients bound to html page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/ingredientlist")
    public ModelAndView getAllIngredients() {
        ModelAndView modelAndView = new ModelAndView("ingredient/ingredient-list");
        modelAndView.addObject("ingredientEntity", ingredientsServiceImpl.findAllIngredients());
        return modelAndView;
    }

    @ApiOperation(value = "Showing Save form for Ingredient ",
            notes = "Returns Form for adding new Ingredient")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/saveingredient")
    public ModelAndView saveForm() {
        ModelAndView modelAndView = new ModelAndView("ingredient/ingredient-form");
        IngredientDto ingredientDto = new IngredientDto();
        modelAndView.addObject("ingredientEntity", ingredientDto);
        return modelAndView;
    }

    @ApiOperation(value = "Save Ingredient",
            notes = "Saving IngredientDto, redirecting to /ingredientlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @PostMapping("/saveingredient")
    public String saveIngredient(@ModelAttribute("ingredientEntity") IngredientDto IngredientDto) {
        ingredientsServiceImpl.saveIngredient(IngredientDto);
        return "redirect:/ingredientlist";
    }

    @ApiOperation(value = "Update Ingredient",
            notes = "Updates IngredientDto, redirecting to /ingredientlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @PostMapping("/updateingredient")
    public String updateIngredient(@RequestParam("ingredientId") int ingredientId, Model model) {
        Optional<IngredientDto> ingredientDto = ingredientsServiceImpl.findIngredientById(ingredientId);
        model.addAttribute("ingredientEntity", ingredientDto);
        return "ingredient/ingredient-form";
    }

    @ApiOperation(value = "Delete Ingredient",
            notes = "Deletes IngredientDto, redirecting to /ingredientlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @PostMapping("/deleteingredient")
    public String deleteIngredient(@RequestParam("ingredientId") int ingredientId) {
        ingredientsServiceImpl.deleteIngredientById(ingredientId);
        return "redirect:/ingredientlist";
    }
}