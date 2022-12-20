package com.reanima.controller;

import com.reanima.business.repository.IngredientsRepo;
import com.reanima.business.repository.model.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IngredientController {

    @Autowired
    private IngredientsRepo ingredientsRepo;
    //html+css view
    @GetMapping({"/ingredientlist"})
    public ModelAndView getAllIngredients() {
        ModelAndView mav2 = new ModelAndView("ingredientlist");
        mav2.addObject("ingredients", ingredientsRepo.findAll());
        return mav2;
    }
    //postman request
    @PostMapping("/addingredient")
    public Ingredients addIngredients(@RequestBody Ingredients ingredients) {
        return ingredientsRepo.save(ingredients);
    }
    //json view
    @GetMapping("/ingredients")
    public List<Ingredients> getIngredients() {
        return ingredientsRepo.findAll();
    }
}