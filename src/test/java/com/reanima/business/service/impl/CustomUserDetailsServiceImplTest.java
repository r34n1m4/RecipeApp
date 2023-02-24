package com.reanima.business.service.impl;

import com.reanima.business.repository.UserRepository;
import com.reanima.business.repository.model.UserEntity;
import com.reanima.business.security.CustomUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.reanima.util.UserUtil.USER_EMAIL;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsServiceImpl customUserDetailsService;

    @Before
    public void setUp() {
        customUserDetailsService = new CustomUserDetailsServiceImpl();
        customUserDetailsService.userRepository = userRepository;
    }

    @Test
    public void testLoadUserByUsername() {
        String userEmail = USER_EMAIL;
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(userEmail);
        when(userRepository.findUserByEmail(userEmail)).thenReturn(userEntity);

        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userEmail);

        verify(userRepository, times(1)).findUserByEmail(userEmail);
        assert userDetails.getUsername().equals(userEmail);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {

        String userEmail = "user@example.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(userEmail);
        when(userRepository.findUserByEmail(userEmail)).thenReturn(null);

        customUserDetailsService.loadUserByUsername(userEmail);
    }

    @Test
    public void testLoadUserByUsernameEmptyEmail() {
        String userEmail = "";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserEmail(userEmail);
        when(userRepository.findUserByEmail(userEmail)).thenReturn(userEntity);

        customUserDetailsService.loadUserByUsername(userEmail);

    }

}

