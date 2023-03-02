package com.reanima.business.security;

import com.reanima.business.repository.model.RoleEntity;
import com.reanima.business.repository.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static com.reanima.util.UserUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CustomUserDetailsTest {

    private UserEntity userEntity;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    public void setUp() {
        userEntity = mock(UserEntity.class);
        customUserDetails = new CustomUserDetails(userEntity);
    }

    @Test
    public void testGetAuthorities() {

        RoleEntity roleEntityAdmin = new RoleEntity();
        roleEntityAdmin.setRoleName("ROLE_ADMIN");
        RoleEntity roleEntityUser = new RoleEntity();
        roleEntityUser.setRoleName("ROLE_USER");
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleEntityAdmin);
        roles.add(roleEntityUser);
        when(userEntity.getRoles()).thenReturn(roles);

        Set<GrantedAuthority> authorities = new HashSet<>(customUserDetails.getAuthorities());

        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    public void testGetPassword() {

        when(userEntity.getUserPassword()).thenReturn(USER_PASSWORD);
        String password = customUserDetails.getPassword();
        assertEquals(USER_PASSWORD, password);
    }

    @Test
    public void testGetUsername() {

        when(userEntity.getUserEmail()).thenReturn(USER_EMAIL);
        String username = customUserDetails.getUsername();
        assertEquals(USER_EMAIL, username);
    }

    @Test
    public void testIsAccountNonExpired() {

        boolean isAccountNonExpired = customUserDetails.isAccountNonExpired();
        assertTrue(isAccountNonExpired);
    }

    @Test
    public void testIsAccountNonLocked() {

        boolean isAccountNonLocked = customUserDetails.isAccountNonLocked();
        assertTrue(isAccountNonLocked);
    }

    @Test
    public void testIsCredentialsNonExpired() {

        boolean isCredentialsNonExpired = customUserDetails.isCredentialsNonExpired();
        assertTrue(isCredentialsNonExpired);
    }

    @Test
    public void testIsEnabled() {

        boolean isEnabled = customUserDetails.isEnabled();
        assertTrue(isEnabled);
    }

    @Test
    public void testGetFullName() {

        when(userEntity.getUserName()).thenReturn(USER_NAME);
        when(userEntity.getUserSurname()).thenReturn(USER_SURNAME);
        String fullName = customUserDetails.getFullName();
        assertEquals(USER_NAME + " " + USER_SURNAME, fullName);
    }
}
