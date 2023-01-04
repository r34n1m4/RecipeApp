package com.reanima.controller;

import com.reanima.business.repository.IngredientRepository;
import com.reanima.business.repository.model.Ingredient;
import com.reanima.business.service.IngredientServiceImpl;
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
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientsRepository;

    @Autowired
    private IngredientServiceImpl ingredientsServiceImpl;

    //html+css view
    @GetMapping({"/ingredientlist"})
    public ModelAndView getAllIngredients() {
        ModelAndView modelAndView = new ModelAndView("ingredient/ingredient-list");
        modelAndView.addObject("ingredient", ingredientsServiceImpl.findAll());
        return modelAndView;
    }

    //controller: show save form
    @GetMapping({"/saveingredient"})
    public ModelAndView addForm() {
        ModelAndView modelAndView = new ModelAndView("ingredient/ingredient-form");
        Ingredient ingredient = new Ingredient();
        modelAndView.addObject("ingredient", ingredient);
        return modelAndView;
    }
    //controller: save form
    @PostMapping("/saveingredient")
    public String saveIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientsServiceImpl.save(ingredient);
        return "redirect:/ingredientlist";
    }
    //controller: update form and save changes
    @PostMapping("/updateingredient")
    public String updateIngredient(@RequestParam("ingredientId") int ingredientId, Model model) {
        Optional<Ingredient> ingredient = ingredientsServiceImpl.findById(ingredientId);
        model.addAttribute("ingredient", ingredient);
        return "ingredient/ingredient-form";
    }
    //controller: delete
    @PostMapping("/deleteingredient")
    public String deleteIngredient(@RequestParam("ingredientId") int ingredientId) {
        ingredientsServiceImpl.deleteById(ingredientId);
        return "redirect:/ingredientlist";
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