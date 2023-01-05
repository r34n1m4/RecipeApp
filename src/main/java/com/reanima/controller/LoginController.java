package com.reanima.controller;

import com.reanima.business.repository.model.User;
import com.reanima.business.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //controller: show login/register page
    @GetMapping("/login")
    public String login() {
        return "user/login-form";
    }

    //controller: show save form
    @GetMapping({"/register"})
    public ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView("user/registration-form");
        User user = new User();
        modelAndView.addObject("user_registration", user);
        return modelAndView;
    }
    //controller: save user
    @PostMapping("/process-register")
    public String registerUser(@ModelAttribute("user_registration") User user) {
        userServiceImpl.save(user);
        return "user/registration-success";
    }

}
