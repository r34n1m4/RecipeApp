package com.reanima.business.service;

import com.reanima.business.repository.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> findAllUsers();

    Optional<UserEntity> findById(int userId);

    void saveUser(UserEntity userEntity);

    void deleteById(int userId);

}
