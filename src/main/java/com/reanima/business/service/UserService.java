package com.reanima.business.service;

import com.reanima.business.model.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAllUsers();

    Optional<UserDto> findById(int userId);

    void saveUser(UserDto userDto);

    void deleteById(int userId);

}
