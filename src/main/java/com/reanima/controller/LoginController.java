package com.reanima.controller;

import com.reanima.business.model.UserDto;
import com.reanima.business.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

import static com.reanima.swagger.ApiResponseMessages.*;
import static com.reanima.swagger.SwaggerTags.LOGIN_CONTROLLER_TAG_NAME;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/api/user")
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
    public String home() {
        return "home";
    }

    @ApiOperation(value = "Login Page",
            notes = "Login Page redirects to Home Page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @GetMapping("/login")
    public String login() {
        return "user/login";
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

    @ApiOperation(value = "Update User",
            notes = "Updating UserDto, redirecting to /userlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@ModelAttribute("userDto") UserDto userDto) {
        userServiceImpl.updateUser(userDto);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/user/userlist")
                .build();
    }

    @ApiOperation(value = "Update User Form",
            notes = "Updates UserDto, redirecting to /userlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/updateuserform", method = RequestMethod.POST)
    public String updateUserForm(@RequestParam("userId") int userId, Model model) {
        Optional<UserDto> userDto = userServiceImpl.findUserById(userId);
        model.addAttribute("userDto", userDto);
        return "user/user-form-update";
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
    @GetMapping("/userlist")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("user/user-list");
        modelAndView.addObject("userList", userServiceImpl.findAllUsers());
        return modelAndView;
    }

    @ApiOperation(value = "Delete User",
            notes = "Deletes UserDto, redirecting to /userlist page")
    @ApiResponses({
            @ApiResponse(code = BAD_REQUEST_CODE, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = NOT_FOUND_CODE, message = NOT_FOUND_MESSAGE),
            @ApiResponse(code = INTERNAL_SERVER_ERROR_CODE, message = INTERNAL_SERVER_ERROR_MESSAGE),
            @ApiResponse(code = OK_CODE, message = OK_MESSAGE)
    })
    @ResponseStatus(OK)
    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteUser(@RequestParam("userId") int userId) {
        userServiceImpl.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/user/userlist")
                .build();
    }


}
