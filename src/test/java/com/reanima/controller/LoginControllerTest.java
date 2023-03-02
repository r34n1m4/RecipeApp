package com.reanima.controller;

import com.reanima.business.model.UserDto;
import com.reanima.business.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.reanima.util.UserUtil.USER_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void testHome() {
        String expected = "home";
        String actual = loginController.home();
        assertEquals(expected, actual);
    }

    @Test
    public void testLogin() {
        String expected = "user/login";
        String actual = loginController.login();
        assertEquals(expected, actual);
    }

    @Test
    public void testLogout() {
        String expected = "home";
        String actual = loginController.logout();
        assertEquals(expected, actual);
    }

    @Test
    public void testRestrictedAccess() {
        String expected = "restricted_access";
        String actual = loginController.restrictedAccess();
        assertEquals(expected, actual);
    }

    @Test
    public void testRegistrationForm() {
        ModelAndView expected = new ModelAndView("user/registration-form");
        expected.addObject("users_registration", new UserDto());

        ModelAndView actual = loginController.registrationForm();
        assertEquals(expected.getViewName(), actual.getViewName());
        assertEquals(expected.getModel().get("users_registration"), actual.getModel().get("users_registration"));
    }

    @Test
    public void testRegisterUser() {
        UserDto user = new UserDto();
        user.setUserPassword(USER_PASSWORD);
        String encodedPassword = passwordEncoder.encode(USER_PASSWORD);

        when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(encodedPassword);
        when(userServiceImpl.saveUser(user)).thenReturn(user);
        String expected = "user/registration-success";
        String actual = loginController.registerUser(user);
        assertEquals(expected, actual);
        verify(userServiceImpl, times(1)).saveUser(user);
        verify(passwordEncoder, times(1)).encode(USER_PASSWORD);
    }



    @Test
    public void testUpdateUser() {
        UserDto user = new UserDto();

        ModelAndView modelAndView = new ModelAndView("user/registration-form");
        modelAndView.addObject("users_registration", user);

        ResponseEntity<Void> expected = ResponseEntity.status(302)
                .header("Location", "/api/user/userlist")
                .build();

        ResponseEntity<Void> actual = loginController.updateUser(user);

        assertEquals(expected.getStatusCodeValue(), actual.getStatusCodeValue());
        assertEquals(expected.getHeaders().getLocation(), actual.getHeaders().getLocation());
        verify(userServiceImpl, times(1)).updateUser(user);
    }

    @Test
    public void updateUserForm_shouldReturnUserFormUpdateView() {
        int userId = 1;
        Model model = new ExtendedModelMap();
        Optional<UserDto> userDto = Optional.of(new UserDto());
        when(userServiceImpl.findUserById(userId)).thenReturn(userDto);

        String result = loginController.updateUserForm(userId, model);

        assertEquals("user/user-form-update", result);
        assertTrue(model.containsAttribute("userDto"));
    }

    @Test
    public void getAllUsers_shouldReturnUserListView() {
        ModelAndView expected = new ModelAndView("user/user-list");
        List<UserDto> userList = Arrays.asList(new UserDto(), new UserDto());
        when(userServiceImpl.findAllUsers()).thenReturn(userList);

        ModelAndView result = loginController.getAllUsers();

        assertEquals(expected.getViewName(), result.getViewName());
        assertEquals(userList, result.getModel().get("userList"));
    }

    @Test
    public void deleteUser_shouldDeleteUserAndRedirectToUserList() {
        int userId = 1;
        ResponseEntity<Void> expected = ResponseEntity
                .status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "/api/user/userlist")
                .build();

        ResponseEntity<Void> result = loginController.deleteUser(userId);

        verify(userServiceImpl, times(1)).deleteUserById(userId);
        assertEquals(expected.getStatusCode(), result.getStatusCode());
        assertEquals(expected.getHeaders(), result.getHeaders());
    }
}
