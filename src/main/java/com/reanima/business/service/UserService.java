package com.reanima.business.service;

import com.reanima.business.model.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDto> findAllUsers();

    Optional<UserDto> findUserById(int userId);

    UserDto saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUserById(int userId);

}
