package com.reanima.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

import static com.reanima.business.util.LogMessages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class RecipeDto {

    @NumberFormat
    private int recipeId;

//    @NotNull(message = FIELD_NOT_NULL)
    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String recipeName;

    @NotNull(message = FIELD_NOT_NULL)
    @Size(max = 100, message = FIELD_MAX_LENGTH_100)
    private String recipeDescription;

    @Size(max = 100, message = FIELD_MAX_LENGTH_100)
    private String recipePreparation;

    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String cuisineType;

    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String dishType;

//    @NotNull(message = FIELD_NOT_NULL)
    private LocalDateTime recipeCreated;

}
