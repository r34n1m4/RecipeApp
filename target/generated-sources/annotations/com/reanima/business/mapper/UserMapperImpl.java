package com.reanima.business.mapper;

import com.reanima.business.model.RoleDto;
import com.reanima.business.model.UserDto;
import com.reanima.business.repository.model.RoleEntity;
import com.reanima.business.repository.model.UserEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-10T10:32:04+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.14.1 (JetBrains s.r.o.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto entityToDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( userEntity.getUserId() );
        userDto.setUserEmail( userEntity.getUserEmail() );
        userDto.setUserPassword( userEntity.getUserPassword() );
        userDto.setUserName( userEntity.getUserName() );
        userDto.setUserSurname( userEntity.getUserSurname() );
        userDto.setRoles( roleEntitySetToRoleDtoSet( userEntity.getRoles() ) );

        return userDto;
    }

    @Override
    public UserEntity dtoToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setUserId( userDto.getUserId() );
        userEntity.setUserEmail( userDto.getUserEmail() );
        userEntity.setUserPassword( userDto.getUserPassword() );
        userEntity.setUserName( userDto.getUserName() );
        userEntity.setUserSurname( userDto.getUserSurname() );
        userEntity.setRoles( roleDtoSetToRoleEntitySet( userDto.getRoles() ) );

        return userEntity;
    }

    protected RoleDto roleEntityToRoleDto(RoleEntity roleEntity) {
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

    protected Set<RoleDto> roleEntitySetToRoleDtoSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDto> set1 = new LinkedHashSet<RoleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRoleDto( roleEntity ) );
        }

        return set1;
    }

    protected RoleEntity roleDtoToRoleEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setRoleId( roleDto.getRoleId() );
        roleEntity.setRoleName( roleDto.getRoleName() );

        return roleEntity;
    }

    protected Set<RoleEntity> roleDtoSetToRoleEntitySet(Set<RoleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleEntity> set1 = new LinkedHashSet<RoleEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDto roleDto : set ) {
            set1.add( roleDtoToRoleEntity( roleDto ) );
        }

        return set1;
    }
}
