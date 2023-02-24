package com.reanima.util;

import com.reanima.business.model.RoleDto;
import com.reanima.business.model.UserDto;
import com.reanima.business.repository.model.RoleEntity;
import com.reanima.business.repository.model.UserEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.reanima.util.CommonUtil.VALID_ID;

public class UserUtil {

    public static final String USER_URL = "/api/user";

    public static final String USER_EMAIL = "randomuser@gmail.com";

    public static final String USER_PASSWORD = "password";

    public static final String USER_NAME = "name";

    public static final String USER_SURNAME = "surname";

    public static final boolean USER_IS_ENABLED = true;

    public static final Set<RoleEntity> ROLE_ENTITY_SET = new HashSet<>();

    public static final Set<RoleDto> ROLE_DTO_SET = new HashSet<>();

    public static UserEntity userEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(VALID_ID);
        userEntity.setUserEmail(USER_EMAIL);
        userEntity.setUserPassword(USER_PASSWORD);
        userEntity.setUserName(USER_NAME);
        userEntity.setUserSurname(USER_SURNAME);
        return userEntity;
    }

    public static UserDto userResponse (UserEntity userEntity) {
        UserDto userResponse = new UserDto();
        userResponse.setUserId(userEntity.getUserId());
        userResponse.setUserEmail(userEntity.getUserEmail());
        userResponse.setUserPassword(userEntity.getUserPassword());
        userResponse.setUserName(userEntity.getUserName());
        userResponse.setUserSurname(userEntity.getUserSurname());
        return userResponse;
    }
}
