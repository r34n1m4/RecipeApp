package com.reanima.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

import static com.reanima.business.util.LogMessages.FIELD_NOT_BLANK;

@Validated
//@NotNull
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeDto {

    @NumberFormat
    private int recipeId;

    @NotBlank(message = FIELD_NOT_BLANK)
    private String recipeName;


    private String recipeDescription;


    private String recipePreparation;


    private String cuisineType;


    private String dishType;
}
