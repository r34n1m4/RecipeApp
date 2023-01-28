package com.reanima.business.mapper;

import com.reanima.business.model.UserDto;
import com.reanima.business.repository.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto (UserEntity userEntity);

    UserEntity dtoToEntity (UserDto userDto);
}
