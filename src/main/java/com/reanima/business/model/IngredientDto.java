package com.reanima.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.reanima.business.util.LogMessages.FIELD_MAX_LENGTH_50;
import static com.reanima.business.util.LogMessages.FIELD_NOT_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class IngredientDto {

    @NumberFormat
    private int ingredientId;

    @NotNull(message = FIELD_NOT_NULL)
    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String ingredientName;

    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String ingredientDescription;

    @Size(max = 50, message = FIELD_MAX_LENGTH_50)
    private String ingredientType;

    @NumberFormat
    private float calories;

    @NumberFormat
    private float carbohydrates;

    @NumberFormat
    private float fat;

    @NumberFormat
    private float protein;

    @NumberFormat
    private float water;

    @NumberFormat
    private float cholesterol;

    private LocalDateTime ingredientCreated;

}
