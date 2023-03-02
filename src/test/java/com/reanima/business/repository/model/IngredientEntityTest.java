package com.reanima.business.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDateTime;

import static com.reanima.util.CommonUtil.VALID_ID;
import static com.reanima.util.IngredientUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class IngredientEntityTest {
    
    private IngredientEntity ingredientEntity;
    private LocalDateTime localDateTime;
    
    @BeforeEach
    public void setUp() {
        ingredientEntity = new IngredientEntity();
        localDateTime = LocalDateTime.now();
    }

    @Test
    void testGettersAndSetters() {

        ingredientEntity.setIngredientId(VALID_ID);
        ingredientEntity.setIngredientName(INGREDIENT_NAME);
        ingredientEntity.setIngredientDescription(INGREDIENT_DESCRIPTION);
        ingredientEntity.setIngredientType(INGREDIENT_TYPE);
        ingredientEntity.setCalories(CALORIES);
        ingredientEntity.setCarbohydrates(CARBOHYDRATES);
        ingredientEntity.setFat(FAT);
        ingredientEntity.setProtein(PROTEIN);
        ingredientEntity.setWater(WATER);
        ingredientEntity.setCholesterol(CHOLESTEROL);
        ingredientEntity.setIngredientCreated(localDateTime);

        assertEquals(VALID_ID, ingredientEntity.getIngredientId());
        assertEquals(INGREDIENT_NAME, ingredientEntity.getIngredientName());
        assertEquals(INGREDIENT_DESCRIPTION, ingredientEntity.getIngredientDescription());
        assertEquals(INGREDIENT_TYPE, ingredientEntity.getIngredientType());
        assertEquals(CALORIES, ingredientEntity.getCalories());
        assertEquals(CARBOHYDRATES, ingredientEntity.getCarbohydrates());
        assertEquals(FAT, ingredientEntity.getFat());
        assertEquals(PROTEIN, ingredientEntity.getFat());
        assertEquals(WATER, ingredientEntity.getWater());
        assertEquals(CHOLESTEROL, ingredientEntity.getCholesterol());
        assertEquals(localDateTime, ingredientEntity.getIngredientCreated());

    }

    @Test
    void testEqualsAndHashCode() {
        IngredientEntity ingredient1 = new IngredientEntity();
        ingredient1.setIngredientId(VALID_ID);
        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setIngredientId(VALID_ID);
        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setIngredientId(VALID_ID + 1);

        assertEquals(ingredient1, ingredient2);
        assertEquals(ingredient1.hashCode(), ingredient2.hashCode());
        assertNotEquals(ingredient1, ingredient3);
        assertNotEquals(ingredient1.hashCode(), ingredient3.hashCode());
    }

    @Test
    void testToString() {
        ingredientEntity.setIngredientId(VALID_ID);
        ingredientEntity.setIngredientName(INGREDIENT_NAME);
        ingredientEntity.setIngredientDescription(INGREDIENT_DESCRIPTION);
        ingredientEntity.setIngredientType(INGREDIENT_TYPE);
        ingredientEntity.setCalories(CALORIES);
        ingredientEntity.setCarbohydrates(CARBOHYDRATES);
        ingredientEntity.setFat(FAT);
        ingredientEntity.setProtein(PROTEIN);
        ingredientEntity.setWater(WATER);
        ingredientEntity.setCholesterol(CHOLESTEROL);
        ingredientEntity.setIngredientCreated(localDateTime);

        String expected = "IngredientEntity(ingredientId=1," +
                " ingredientName=Potato," +
                " ingredientDescription=Simple as potato," +
                " ingredientType=Vegetable," +
                " calories=0.5," +
                " carbohydrates=0.5," +
                " fat=0.5," +
                " protein=0.5," +
                " water=0.5," +
                " cholesterol=0.5," +
                " ingredientCreated=" +
                localDateTime + ")";

        assertEquals(expected, ingredientEntity.toString());
    }

    @Test
    void testAllArgsConstructor() {

        ingredientEntity = new IngredientEntity(
                VALID_ID,
                INGREDIENT_NAME,
                INGREDIENT_DESCRIPTION,
                INGREDIENT_TYPE,
                CALORIES,
                CARBOHYDRATES,
                FAT,
                PROTEIN,
                WATER,
                CHOLESTEROL,
                localDateTime
        );

        assertEquals(VALID_ID, ingredientEntity.getIngredientId());
        assertEquals(INGREDIENT_NAME, ingredientEntity.getIngredientName());
        assertEquals(INGREDIENT_DESCRIPTION, ingredientEntity.getIngredientDescription());
        assertEquals(INGREDIENT_TYPE, ingredientEntity.getIngredientType());
        assertEquals(CALORIES, ingredientEntity.getCalories());
        assertEquals(CARBOHYDRATES, ingredientEntity.getCarbohydrates());
        assertEquals(FAT, ingredientEntity.getFat());
        assertEquals(PROTEIN, ingredientEntity.getProtein());
        assertEquals(WATER, ingredientEntity.getWater());
        assertEquals(CHOLESTEROL, ingredientEntity.getCholesterol());
        assertEquals(localDateTime, ingredientEntity.getIngredientCreated());
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(ingredientEntity);
    }

    @Test
    void testBuilder() {
        ingredientEntity = IngredientEntity.builder()
                .ingredientId(VALID_ID)
                .ingredientName(INGREDIENT_NAME)
                .ingredientDescription(INGREDIENT_DESCRIPTION)
                .ingredientType(INGREDIENT_TYPE)
                .calories(CALORIES)
                .carbohydrates(CARBOHYDRATES)
                .fat(FAT)
                .protein(PROTEIN)
                .water(WATER)
                .cholesterol(CHOLESTEROL)
                .ingredientCreated(localDateTime)
                .build();

        assertEquals(VALID_ID, ingredientEntity.getIngredientId());
        assertEquals(INGREDIENT_NAME, ingredientEntity.getIngredientName());
        assertEquals(INGREDIENT_DESCRIPTION, ingredientEntity.getIngredientDescription());
        assertEquals(INGREDIENT_TYPE, ingredientEntity.getIngredientType());
        assertEquals(CALORIES, ingredientEntity.getCalories());
        assertEquals(CARBOHYDRATES, ingredientEntity.getCarbohydrates());
        assertEquals(FAT, ingredientEntity.getFat());
        assertEquals(PROTEIN, ingredientEntity.getProtein());
        assertEquals(WATER, ingredientEntity.getWater());
        assertEquals(CHOLESTEROL, ingredientEntity.getCholesterol());
        assertEquals(localDateTime, ingredientEntity.getIngredientCreated());
    }

}