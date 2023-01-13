package com.reanima.controller;

import com.reanima.business.repository.model.UserEntity;
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
        UserEntity userEntity = new UserEntity();
        modelAndView.addObject("user_registration", userEntity);
        return modelAndView;
    }
    //controller: save user
    @PostMapping("/process-register")
    public String registerUser(@ModelAttribute("user_registration") UserEntity userEntity) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(userEntity.getUserPassword());
        userEntity.setUserPassword(encodePassword);
        userServiceImpl.saveUser(userEntity);
        return "user/registration-success";
    }
    //controller: list-users for tests
    @GetMapping("/list-users")
    public String listUsers(Model model) {
        List<UserEntity> userEntity = userServiceImpl.findAllUsers();
        model.addAttribute("listUsers", userEntity);
        return "user/list-users";
    }
}
