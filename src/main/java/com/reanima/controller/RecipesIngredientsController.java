package com.reanima.controller;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.model.RecipeDto;
import com.reanima.business.model.RecipesIngredientsDto;
import com.reanima.business.service.impl.RecipesIngredientsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/recipes-ingredients")
public class RecipesIngredientsController {

    @Autowired
    RecipesIngredientsServiceImpl recipesIngredientsServiceImpl;

    @GetMapping("/all")
    public ModelAndView getAllRecipesIngredients() {
        List<RecipesIngredientsDto> recipesIngredients = recipesIngredientsServiceImpl.getAllRecipesIngredients();
        ModelAndView modelAndView = new ModelAndView("recipesIngredients");
        modelAndView.addObject("recipesIngredients", recipesIngredients);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getRecipesIngredientsById(@PathVariable int id) {
        RecipesIngredientsDto recipesIngredients = recipesIngredientsServiceImpl.getRecipesIngredientsById(id);
        ModelAndView modelAndView = new ModelAndView("recipeIngredient");
        modelAndView.addObject("recipesIngredients", recipesIngredients);
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView saveRecipesIngredients(@ModelAttribute("recipesIngredients") RecipesIngredientsDto recipesIngredients) {
        RecipesIngredientsDto savedRecipesIngredients = recipesIngredientsServiceImpl.saveRecipesIngredients(recipesIngredients);
        ModelAndView modelAndView = new ModelAndView("redirect:/recipes-ingredients/all");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteRecipesIngredients(@PathVariable int id) {
        recipesIngredientsServiceImpl.deleteRecipesIngredients(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/recipes-ingredients/all");
        return modelAndView;
    }

    @GetMapping("/findByRecipe")
    public ModelAndView findByRecipeDto(@ModelAttribute("recipe") RecipeDto recipeDto) {
        List<RecipesIngredientsDto> recipesIngredients = recipesIngredientsServiceImpl.findByRecipeDto(recipeDto);
        ModelAndView modelAndView = new ModelAndView("recipesIngredients");
        modelAndView.addObject("recipesIngredients", recipesIngredients);
        return modelAndView;
    }

    @GetMapping("/findByIngredient")
    public ModelAndView findByIngredientDto(@ModelAttribute("ingredient") IngredientDto ingredientDto) {
        List<RecipesIngredientsDto> recipesIngredients = recipesIngredientsServiceImpl.findByIngredientDto(ingredientDto);
        ModelAndView modelAndView = new ModelAndView("recipesIngredients");
        modelAndView.addObject("recipesIngredients", recipesIngredients);
        return modelAndView;
    }

    @GetMapping("/findByRecipeName")
    public ModelAndView findByRecipeName(@RequestParam("recipeName") String recipeName) {
        List<RecipesIngredientsDto> recipesIngredients = recipesIngredientsServiceImpl.findByRecipeName(recipeName);
        ModelAndView modelAndView = new ModelAndView("recipesIngredients");
        modelAndView.addObject("recipesIngredients", recipesIngredients);
        return modelAndView;
    }
}
