package com.reanima.business.service.impl;

import com.reanima.business.repository.UserRepository;
import com.reanima.business.repository.model.UserEntity;
import com.reanima.business.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(userEmail);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(userEntity);
    }
}
