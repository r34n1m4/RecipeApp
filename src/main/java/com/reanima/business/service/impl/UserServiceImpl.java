package com.reanima.business.service.impl;

import com.reanima.business.handler.exception.EmailExistsException;
import com.reanima.business.mapper.UserMapper;
import com.reanima.business.model.UserDto;
import com.reanima.business.repository.UserRepository;
import com.reanima.business.repository.model.UserEntity;
import com.reanima.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDto> findAllUsers() {

        List<UserEntity> userEntity = userRepository.findAll();
        return userEntity.stream().map(userMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findUserById(int userId) {

        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntity.map(userMapper::entityToDto);
    }

    public void saveUser(UserDto userDto) {
        UserEntity userEntity;
        userEntity = userRepository.findUserByEmail(userDto.getUserEmail());
        if (userEntity != null) {
            throw new EmailExistsException("There is account already registered with email: "
                    + userDto.getUserEmail() + "\nPlease chose another email.");
        } else {
            userEntity = userRepository.save(userMapper.dtoToEntity(userDto));
            userEntity.setEnabled(true);
        }
        userMapper.entityToDto(userEntity);
    }

    @Override
    public void updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.save(userMapper.dtoToEntity(userDto));
        userMapper.entityToDto(userEntity);
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}
