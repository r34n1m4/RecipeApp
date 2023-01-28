package com.reanima.controller;

import com.reanima.business.model.UserDto;
import com.reanima.business.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //controller: show home page
    @GetMapping("/home")
    public String login() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "home";
    }

    //controller: show save form
    @GetMapping({"/register"})
    public ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView("user/registration-form");
        UserDto userDto = new UserDto();
        modelAndView.addObject("users_registration", userDto);
        return modelAndView;
    }
    //controller: save user
    @PostMapping("/process-register")
    public String registerUser(@ModelAttribute("users_registration") UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(userDto.getUserPassword());
        userDto.setUserPassword(encodePassword);
        userServiceImpl.saveUser(userDto);
        return "user/registration-success";
    }

    //controller: user list for ADMIN
    @GetMapping("/list-users")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("user/list-users");
        modelAndView.addObject("usersList", userServiceImpl.findAllUsers());
        return modelAndView;
    }

    //controller: redirect to restriction access page
    @GetMapping("/restricted_access")
    public String restrictedAccess() {
        return "restricted_access";
    }
}
