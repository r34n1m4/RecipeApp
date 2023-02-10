package com.reanima.controller;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.service.impl.IngredientServiceImpl;
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
import static com.reanima.swagger.SwaggerTags.INGREDIENT_CONTROLLER_TAG_NAME;
import static org.springframework.http.HttpStatus.OK;


@Controller
@RequestMapping("/api/ingredient")
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

    @ApiOperation(value = "Save Ingredient Form",
            notes = "Returns Form for adding new Ingredient")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/saveingredient", method = RequestMethod.GET)
    public ModelAndView saveIngredientForm() {
        ModelAndView modelAndView = new ModelAndView("ingredient/ingredient-form-save");
        IngredientDto ingredientDto = new IngredientDto();
        modelAndView.addObject("ingredientDto", ingredientDto);
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
    @RequestMapping(value = "/saveingredient", method = RequestMethod.POST)
    public ResponseEntity<Void> saveIngredient(@ModelAttribute("ingredientDto") IngredientDto ingredientDto) {
        ingredientsServiceImpl.saveIngredient(ingredientDto);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/ingredient/ingredientlist")
                .build();
    }

    @ApiOperation(value = "Update Ingredient",
            notes = "Updating IngredientDto, redirecting to /ingredientlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/updateingredient", method = RequestMethod.POST)
    public ResponseEntity<Void> updateIngredient(@ModelAttribute("ingredientDto") IngredientDto ingredientDto) {
        ingredientsServiceImpl.updateIngredient(ingredientDto);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/ingredient/ingredientlist")
                .build();
    }

    @ApiOperation(value = "Update Ingredient Form",
            notes = "Updates IngredientDto, redirecting to /ingredientlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/updateingredientform", method = RequestMethod.POST)
    public String updateIngredientForm(@RequestParam("ingredientId") int ingredientId, Model model) {
        Optional<IngredientDto> ingredientDto = ingredientsServiceImpl.findIngredientById(ingredientId);
        model.addAttribute("ingredientDto", ingredientDto);
        return "ingredient/ingredient-form-update";
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
    public ResponseEntity<Void> deleteIngredient(@RequestParam("ingredientId") int ingredientId) {
        ingredientsServiceImpl.deleteIngredientById(ingredientId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/ingredient/ingredientlist")
                .build();
    }
}