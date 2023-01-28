package com.reanima.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.reanima.business.util.LogMessages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class RoleDto {

    @NumberFormat
    private int roleId;

    @NotNull(message = FIELD_NOT_NULL)
    @NotEmpty(message = FIELD_NOT_EMPTY)
    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String roleName;

}
