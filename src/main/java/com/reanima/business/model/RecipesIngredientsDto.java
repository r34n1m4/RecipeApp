package com.reanima.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;

import static com.reanima.business.util.LogMessages.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipesIngredientsDto {

    @NumberFormat
    private int recipesIngredientsId;

    @NotBlank(message = FIELD_NOT_BLANK)
    private int recipeId;

    @NotBlank(message = FIELD_NOT_BLANK)
    private String recipeName;

    @NotBlank(message = FIELD_NOT_BLANK)
    private int ingredientId;

    @NotBlank(message = FIELD_NOT_BLANK)
    private String ingredientName;

    @NumberFormat
    private float quantity;

}