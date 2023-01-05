package com.reanima.business.service;

import com.reanima.business.repository.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(int userId);

    User save(User user);

    List<User> deleteById(int userId);
}
