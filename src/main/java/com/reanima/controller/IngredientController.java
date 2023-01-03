package com.reanima.controller;

import com.reanima.business.repository.IngredientsRepository;
import com.reanima.business.service.IngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IngredientController {

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private IngredientServiceImpl ingredientsServiceImpl;

    //html+css view
    @GetMapping({"/ingredientlist"})
    public ModelAndView getAllIngredients() {
        ModelAndView modelAndView = new ModelAndView("ingredients/ingredient-list");
        modelAndView.addObject("ingredient", ingredientsServiceImpl.findAll());
        return modelAndView;
    }
//    //postman
//    @PostMapping("/addingredient")
//    public Ingredients addIngredients(@RequestBody Ingredients ingredients) {
//        return ingredientsRepository.save(ingredients);
//    }
//    //json view
//    @GetMapping("/ingredients")
//    public List<Ingredients> getIngredients() {
//        return ingredientsRepository.findAll();
//    }
}