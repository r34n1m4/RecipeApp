package com.reanima.controller;

import com.reanima.business.repository.RecipeRepo;
import com.reanima.business.repository.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller
public class RecipeController {

    @Autowired
    private RecipeRepo recipeRepo;

    @GetMapping({"/recipelist"})
    public ModelAndView getAllRecipes() {
        ModelAndView mav1 = new ModelAndView("recipelist");
        mav1.addObject("recipe", recipeRepo.findAll());
        return mav1;
    }

    @PostMapping("/addrecipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeRepo.findAll();
    }
}
