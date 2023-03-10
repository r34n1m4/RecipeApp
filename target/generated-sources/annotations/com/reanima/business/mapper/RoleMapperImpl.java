package com.reanima.business.mapper;

import com.reanima.business.model.RoleDto;
import com.reanima.business.repository.model.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-10T10:32:04+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.14.1 (JetBrains s.r.o.)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDto entityToDto(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( roleEntity.getRoleId() != null ) {
            roleDto.setRoleId( roleEntity.getRoleId() );
        }
        roleDto.setRoleName( roleEntity.getRoleName() );

        return roleDto;
    }

    @Override
    public RoleEntity dtoToEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setRoleId( roleDto.getRoleId() );
        roleEntity.setRoleName( roleDto.getRoleName() );

        return roleEntity;
    }
}
