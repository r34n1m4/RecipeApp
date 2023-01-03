package com.reanima.controller;

import com.reanima.business.repository.IngredientsRepository;
import com.reanima.business.repository.model.Ingredients;
import com.reanima.business.repository.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Controller
public class IngredientsController {

    @Autowired
    private IngredientsRepository ingredientsRepository;
    //html+css view
    @GetMapping({"/ingredientlist"})
    public ModelAndView getAllIngredients() {
        ModelAndView modelAndView = new ModelAndView("ingredients/ingredient-list");
        modelAndView.addObject("ingredients_nutrition_1g", ingredientsRepository.findAll());
        return modelAndView;
    }
    //postman
    @PostMapping("/addingredient")
    public Ingredients addIngredients(@RequestBody Ingredients ingredients) {
        return ingredientsRepository.save(ingredients);
    }
    //json view
    @GetMapping("/ingredients")
    public List<Ingredients> getIngredients() {
        return ingredientsRepository.findAll();
    }
}