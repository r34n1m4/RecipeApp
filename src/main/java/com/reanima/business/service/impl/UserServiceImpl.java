package com.reanima.business.service.impl;

import com.reanima.business.repository.UserRepository;
import com.reanima.business.repository.model.UserEntity;
import com.reanima.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    private UserEntity userEntity;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(int userId) {
        return userRepository.findById(userId);
    }

    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }
}
