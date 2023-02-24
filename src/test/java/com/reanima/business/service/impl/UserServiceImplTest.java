package com.reanima.business.service.impl;

import com.reanima.business.handler.exception.UserException;
import com.reanima.business.mapper.UserMapper;
import com.reanima.business.model.UserDto;
import com.reanima.business.repository.UserRepository;
import com.reanima.business.repository.model.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.reanima.business.util.LogMessages.USER_WITH_ID_NOT_FOUND;
import static com.reanima.util.CommonUtil.*;
import static com.reanima.util.UserUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;
    private UserEntity userEntity;
    private UserDto userDto;
    private List<UserEntity> userEntityList;

    @BeforeEach
    public void setUp() {

        userDto = new UserDto(
                VALID_ID,
                USER_EMAIL,
                USER_PASSWORD,
                USER_NAME,
                USER_SURNAME,
                ROLE_DTO_SET
        );

        userEntity = new UserEntity(
                VALID_ID,
                USER_EMAIL,
                USER_PASSWORD,
                USER_NAME,
                USER_SURNAME,
                USER_IS_ENABLED,
                LOCAL_DATE_TIME,
                LOCAL_DATE_TIME,
                ROLE_ENTITY_SET
        );

        userEntityList = new ArrayList<>(List.of(userEntity));
    }

    @Test
    @DisplayName("Test: Find all Users")
    void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(userEntityList);
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);
        List<UserDto> userDtoList = userService.findAllUsers();
        assertEquals(1, userDtoList.size());
        assertEquals(1, userDtoList.get(0).getUserId());
        assertEquals(USER_EMAIL, userDtoList.get(0).getUserEmail());
    }

    @Test
    @DisplayName("Test: Find User by valid ID")
    void testFindUserById_ValidId() throws NoSuchElementException {
        when(userRepository.findById(VALID_ID)).thenReturn(Optional.of(userEntity));
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);
        Optional<UserDto> returnedUser = userService.findUserById(userDto.getUserId());
        assertEquals(userDto.getUserId(), returnedUser.get().getUserId());
        assertEquals(userDto.getUserEmail(), returnedUser.get().getUserEmail());
        assertEquals(userDto.getUserPassword(), returnedUser.get().getUserPassword());
        assertEquals(userDto.getUserName(), returnedUser.get().getUserName());
        assertEquals(userDto.getUserSurname(), returnedUser.get().getUserSurname());
        verify(userRepository, times(1)).findById(VALID_ID);
    }

    @Test
    @DisplayName("Test: Find User by invalid ID")
    void testFindUserById_InvalidId() {
        when(userRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
        assertFalse(userService.findUserById(INVALID_ID).isPresent());
        verify(userRepository, times(1)).findById(INVALID_ID);
    }

    @Test
    @DisplayName("Test: Save User")
    void testSaveUser() {
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);
        when(userMapper.dtoToEntity(userDto)).thenReturn(userEntity);
        UserDto userSaved = userService.saveUser(userDto);
        assertFalse(userService.UserEmailMatch(userSaved));
        assertEquals(userDto, userSaved);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    @DisplayName("Test: Save when User already exist")
    void testSaveUser_WhenAlreadyExist() {
        when(userService.UserEmailMatch(userDto))
                .thenThrow(new UserException("User with this email already exist."));
        Assertions.assertThrows(UserException.class, () -> userService.UserEmailMatch(userDto));
    }

    @Test
    @DisplayName("Test: Update User")
    void testUpdateUser() {
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToDto(userEntity)).thenReturn(userDto);
        when(userMapper.dtoToEntity(userDto)).thenReturn(userEntity);
        UserDto userUpdated = userService.saveUser(userDto);
        assertFalse(userService.UserEmailMatch(userUpdated));
        assertEquals(userDto, userUpdated);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    @DisplayName("Test: Delete User by valid ID")
    void testDeleteUserById_ValidId() {
        userService.deleteUserById((userDto.getUserId()));
        verify(userRepository, times(1)).deleteById(userDto.getUserId());
    }

    @Test
    @DisplayName("Test: Delete User by invalid ID")
    void testDeleteUserById_InvalidId() {
        lenient().doThrow(new UserException(USER_WITH_ID_NOT_FOUND))
                .when(userRepository).deleteById(INVALID_ID);
        Assertions.assertThrows(UserException.class, () -> userService.deleteUserById(INVALID_ID));
        verify(userRepository, times(1)).deleteById(INVALID_ID);
    }

    @Test
    @DisplayName("Test: User name match when found")
    void testUserNameMatch_Found() {
        when(userRepository.findAll()).thenReturn(userEntityList);
        assertTrue(userService.UserEmailMatch(userDto));
    }

    @Test
    @DisplayName("Test: User name match when not found")
    void testUserNameMatch_NotFound() {
        userDto.setUserEmail("Random User Email");
        when(userRepository.findAll()).thenReturn(userEntityList);
        assertFalse(userService.UserEmailMatch(userDto));
    }
}