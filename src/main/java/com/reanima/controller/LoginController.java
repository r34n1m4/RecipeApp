package com.reanima.controller;

import com.reanima.business.repository.model.User;
import com.reanima.business.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //controller: show home page
    @GetMapping("/home")
    public String login() {
        return "home";
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(user.getUserPassword());
        user.setUserPassword(encodePassword);
        userServiceImpl.save(user);
        return "user/registration-success";
    }
    //controller: list-users for tests
    @GetMapping("/list-users")
    public String listUsers(Model model) {
        List<User> listUsers = userServiceImpl.findAll();
        model.addAttribute("listUsers", listUsers);
        return "user/list-users";
    }
}
