package com.reanima.business.service;

import com.reanima.business.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAllUsers();

    Optional<UserDto> findUserById(int userId);

    void saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUserById(int userId);

}
