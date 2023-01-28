package com.reanima.business.mapper;

import com.reanima.business.model.RoleDto;
import com.reanima.business.repository.model.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto entityToDto (RoleEntity roleEntity);

    RoleEntity dtoToEntity (RoleDto roleDto);
}
