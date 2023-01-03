package com.reanima.controller;

import com.reanima.business.repository.RecipeRepository;
import com.reanima.business.repository.model.Recipe;
import com.reanima.business.service.RecipeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeServiceImpl recipeServiceImpl;

    //html+css view
    @GetMapping({"/recipelist"})
    public ModelAndView getAllRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-list");
        modelAndView.addObject("recipe", recipeServiceImpl.findAll());
        return modelAndView;
    }
    //controller: show save form
    @GetMapping({"/saverecipe"})
    public ModelAndView addForm() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-form");
        Recipe recipe = new Recipe();
        modelAndView.addObject("recipe", recipe);
        return modelAndView;
    }
    //controller: save form
    @PostMapping("/saverecipe")
    public String saveRecipe(@ModelAttribute("recipe") Recipe recipe) {
        recipeServiceImpl.save(recipe);
        return "redirect:/recipelist";
    }
    //controller: update form and save changes
    @PostMapping("/updaterecipe")
    public String updateRecipe(@RequestParam("recipeId") int recipeId, Model model) {
        Optional<Recipe> recipe = recipeServiceImpl.findById(recipeId);
        model.addAttribute("recipe", recipe);
        return "recipe/recipe-form";
    }
    //controller: delete
    @PostMapping("/deleterecipe")
    public String deleteRecipe(@RequestParam("recipeId") int recipeId) {
        recipeServiceImpl.deleteById(recipeId);
        return "redirect:/recipelist";
    }
//    //json view
//    @GetMapping("/recipes")
//    public List<Recipe> getRecipe() {
//        return recipeServiceImpl.findAll();
//    }
//    //postman
//    @PostMapping("/recipe/save")
//    public Recipe addRecipe(@RequestBody Recipe recipe) {
//        return recipeServiceImpl.save(recipe);
//    }
//
//    //postman
//    @DeleteMapping({"/recipe/delete/{id}"})
//    public List<Recipe> deleteRecipeById(@PathVariable int id) {
//        return recipeServiceImpl.deleteById(id);
//    }


}
