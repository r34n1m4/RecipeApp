package com.reanima.business.model;

import com.reanima.business.repository.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

import static com.reanima.business.util.LogMessages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class UserDto {

    @NumberFormat
    private int userId;

    @NotNull(message = FIELD_NOT_NULL)
    @NotEmpty(message = FIELD_NOT_EMPTY)
    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String userEmail;

    @NotNull(message = FIELD_NOT_NULL)
    @NotEmpty(message = FIELD_NOT_EMPTY)
    private String userPassword;
    private String matchingPassword;

    @NotNull(message = FIELD_NOT_NULL)
    @NotEmpty(message = FIELD_NOT_EMPTY)
    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String userName;

    @NotNull(message = FIELD_NOT_NULL)
    @NotEmpty(message = FIELD_NOT_EMPTY)
    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String userSurname;

    private Set<RoleDto> roles = new HashSet<>();

    public void addRole(RoleDto roleDto) {
        this.roles.add(roleDto);
    }
}
