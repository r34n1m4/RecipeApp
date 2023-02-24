package com.reanima.business.mapper;

import com.reanima.business.model.UserDto;
import com.reanima.business.repository.model.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reanima.util.UserUtil.userEntity;
import static com.reanima.util.UserUtil.userResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("Test: Entity to DTO equality")
    void entityToDto_receiveEntity_returnResponse() {
        UserEntity userEntity = userEntity();
        UserDto expected = userResponse(userEntity);

        UserDto result = userMapper.entityToDto(userEntity);

        assertEquals(expected, result);
    }
}