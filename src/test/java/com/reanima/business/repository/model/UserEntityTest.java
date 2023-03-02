package com.reanima.business.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.UserUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    private UserEntity userEntity;
    private LocalDateTime localDateTime;
    private Set<RoleEntity> roles;
    private RoleEntity roleEntity;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        localDateTime = LocalDateTime.now();
        roles = new HashSet<>();
        roleEntity = new RoleEntity();
    }

    @Test
    public void testUserId() {
        userEntity.setUserId(VALID_ID);
        assertEquals(VALID_ID, userEntity.getUserId());
    }

    @Test
    public void testUserEmail() {
        userEntity.setUserEmail(USER_EMAIL);
        assertEquals(USER_EMAIL, userEntity.getUserEmail());
    }

    @Test
    public void testUserPassword() {
        userEntity.setUserPassword(USER_PASSWORD);
        assertEquals(USER_PASSWORD, userEntity.getUserPassword());
    }

    @Test
    public void testUserName() {
        userEntity.setUserName(USER_NAME);
        assertEquals(USER_NAME, userEntity.getUserName());
    }

    @Test
    public void testUserSurname() {
        userEntity.setUserSurname(USER_SURNAME);
        assertEquals(USER_SURNAME, userEntity.getUserSurname());
    }

    @Test
    public void testEnabled() {
        userEntity.setEnabled(false);
        assertFalse(userEntity.isEnabled());
    }

    @Test
    public void testUserCreated() {
        userEntity.setUserCreated(localDateTime);
        assertEquals(localDateTime, userEntity.getUserCreated());
    }

    @Test
    public void testUserUpdated() {
        userEntity.setUserUpdated(localDateTime);
        assertEquals(localDateTime, userEntity.getUserUpdated());
    }

    @Test
    public void testRoles() {
        roleEntity.setRoleId(VALID_ID);
        roleEntity.setRoleName(USER_ROLE);
        roles.add(roleEntity);
        userEntity.setRoles(roles);
        assertEquals(roles, userEntity.getRoles());
    }

    @Test
    public void testAddRole() {
        roleEntity.setRoleId(VALID_ID);
        roleEntity.setRoleName(USER_ROLE);
        userEntity.addRole(roleEntity);
        assertTrue(userEntity.getRoles().contains(roleEntity));
    }

    @Test
    public void testAllArgsConstructor() {
        localDateTime = LocalDateTime.now();
        roleEntity.setRoleId(VALID_ID);
        roleEntity.setRoleName(USER_ROLE);
        roles.add(roleEntity);
        userEntity = new UserEntity(
                VALID_ID,
                USER_EMAIL,
                USER_PASSWORD,
                USER_NAME,
                USER_SURNAME,
                false,
                localDateTime,
                localDateTime,
                roles);
        assertNotNull(userEntity);
        assertEquals(VALID_ID, userEntity.getUserId());
        assertEquals(USER_EMAIL, userEntity.getUserEmail());
        assertEquals(USER_PASSWORD, userEntity.getUserPassword());
        assertEquals(USER_NAME, userEntity.getUserName());
        assertEquals(USER_SURNAME, userEntity.getUserSurname());
        assertFalse(userEntity.isEnabled());
        assertEquals(localDateTime, userEntity.getUserCreated());
        assertEquals(localDateTime, userEntity.getUserUpdated());
        assertEquals(roles, userEntity.getRoles());
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(userEntity);
    }

    @Test
    public void testBuilder() {
        localDateTime = LocalDateTime.now();
        roleEntity.setRoleId(VALID_ID);
        roleEntity.setRoleName(USER_ROLE);
        roles.add(roleEntity);
        userEntity = UserEntity.builder()
                .userId(VALID_ID)
                .userEmail(USER_EMAIL)
                .userPassword(USER_PASSWORD)
                .userName(USER_NAME)
                .userSurname(USER_SURNAME)
                .enabled(false)
                .userCreated(localDateTime)
                .userUpdated(localDateTime)
                .roles(roles)
                .build();
        assertNotNull(userEntity);
        assertEquals(VALID_ID, userEntity.getUserId());
        assertEquals(USER_EMAIL, userEntity.getUserEmail());
        assertEquals(USER_PASSWORD, userEntity.getUserPassword());
        assertEquals(USER_NAME, userEntity.getUserName());
        assertEquals(USER_SURNAME, userEntity.getUserSurname());
        assertFalse(userEntity.isEnabled());
        assertEquals(localDateTime, userEntity.getUserCreated());
        assertEquals(localDateTime, userEntity.getUserUpdated());
        assertEquals(roles, userEntity.getRoles());
    }
}

