package com.reanima.controller;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.service.impl.RecipeServiceImpl;
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
    private RecipeServiceImpl recipeServiceImpl;

    //html+css view
    @GetMapping({"/recipelist"})
    public ModelAndView getAllRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-list");
        modelAndView.addObject("recipeEntity", recipeServiceImpl.findAllRecipes());
        return modelAndView;
    }
    //controller: show save form
    @GetMapping({"/saverecipe"})
    public ModelAndView addForm() {
        ModelAndView modelAndView = new ModelAndView("recipe/recipe-form");
        RecipeDto recipeDto = new RecipeDto();
        modelAndView.addObject("recipeDto", recipeDto);
        return modelAndView;
    }
    //controller: save form
    @PostMapping("/saverecipe")
    public String saveRecipe(@ModelAttribute("recipeDto") RecipeDto recipeDto) {
        recipeServiceImpl.saveRecipe(recipeDto);
        return "redirect:/recipelist";
    }
    //controller: update form and save changes
    @PostMapping("/updaterecipe")
    public String updateRecipe(@RequestParam("recipeId") int recipeId, Model model) {
        Optional<RecipeDto> recipeDto = recipeServiceImpl.findRecipeById(recipeId);
        model.addAttribute("recipeDto", recipeDto);
        return "recipe/recipe-form";
    }
    //controller: delete
    @PostMapping("/deleterecipe")
    public String deleteRecipe(@RequestParam("recipeId") int recipeId) {
        recipeServiceImpl.deleteRecipeById(recipeId);
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
