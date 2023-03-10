package com.reanima.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static com.reanima.business.util.LogMessages.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Builder
public class RecipeDto {

    @NumberFormat
    private int recipeId;

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String recipeName;

    @NotBlank(message = FIELD_NOT_BLANK)
    @Size(max = 100, message = FIELD_MAX_LENGTH_100)
    private String recipeDescription;

    @Size(max = 100, message = FIELD_MAX_LENGTH_100)
    private String recipePreparation;

    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String cuisineType;

    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String dishType;

    @NotNull(message = FIELD_NOT_NULL)
    private LocalDateTime recipeCreated;

//    private Set<RecipesIngredientsDto> recipeIngredients = new HashSet<>();

}
