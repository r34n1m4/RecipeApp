package com.reanima.controller;

import com.reanima.business.model.UserDto;
import com.reanima.business.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.reanima.swagger.ApiResponseMessages.*;
import static com.reanima.swagger.ApiResponseMessages.OK_MESSAGE;
import static com.reanima.swagger.SwaggerTags.LOGIN_CONTROLLER_TAG_NAME;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api")
@Api(tags = LOGIN_CONTROLLER_TAG_NAME)
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "Home Page",
            notes = "Home Page with redirecting routing options")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/home")
    public String login() {
        return "home";
    }

    @ApiOperation(value = "Logout Page",
            notes = "Logout Page redirects to Home Page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/logout")
    public String logout() {
        return "home";
    }

    @ApiOperation(value = "Restricted Access Page",
            notes = "Restricted Access Page, informative page for authorization control")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/restricted_access")
    public String restrictedAccess() {
        return "restricted_access";
    }

    @ApiOperation(value = "Registration Form",
            notes = "Registration Form for UserDto")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping({"/register"})
    public ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView("user/registration-form");
        UserDto userDto = new UserDto();
        modelAndView.addObject("users_registration", userDto);
        return modelAndView;
    }

    @ApiOperation(value = "Register User",
            notes = "Registration Form saves UserDto, redirects to /registration-success page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @PostMapping("/process-register")
    public String registerUser(@Valid @ModelAttribute("users_registration") UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(userDto.getUserPassword());
        userDto.setUserPassword(encodePassword);
        userServiceImpl.saveUser(userDto);
        return "user/registration-success";
    }

    @ApiOperation(value = "List User",
            notes = "Find all Users, authorized access only for ADMIN")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/list-users")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("user/list-users");
        modelAndView.addObject("usersList", userServiceImpl.findAllUsers());
        return modelAndView;
    }


}
