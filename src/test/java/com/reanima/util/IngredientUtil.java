package com.reanima.util;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.repository.model.IngredientEntity;

import static com.reanima.util.CommonUtil.VALID_ID;

public class IngredientUtil {

    public static final String INGREDIENT_URL = "/api/ingredient";

    public static final String INGREDIENT_NAME = "Potato";

    public static final String INGREDIENT_DESCRIPTION = "Simple as potato";

    public static final String INGREDIENT_TYPE = "Vegetable";

    public static final float CALORIES = 0.5F;

    public static final float CARBOHYDRATES = 0.5F;

    public static final float FAT = 0.5F;

    public static final float PROTEIN = 0.5F;

    public static final float WATER = 0.5F;

    public static final float CHOLESTEROL = 0.5F;

    public static IngredientEntity ingredientEntity() {
        IngredientEntity ingredientEntity = new IngredientEntity();
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
        return ingredientEntity;
    }

    public static IngredientDto ingredientResponse (IngredientEntity ingredientEntity) {
        IngredientDto ingredientResponse = new IngredientDto();
        ingredientResponse.setIngredientId(ingredientEntity.getIngredientId());
        ingredientResponse.setIngredientName(ingredientEntity.getIngredientName());
        ingredientResponse.setIngredientDescription(ingredientEntity.getIngredientDescription());
        ingredientResponse.setIngredientType(ingredientEntity.getIngredientType());
        ingredientResponse.setCalories(ingredientEntity.getCalories());
        ingredientResponse.setCarbohydrates(ingredientEntity.getCarbohydrates());
        ingredientResponse.setFat(ingredientEntity.getFat());
        ingredientResponse.setProtein(ingredientEntity.getProtein());
        ingredientResponse.setWater(ingredientEntity.getWater());
        ingredientResponse.setCholesterol(ingredientEntity.getCholesterol());
        return ingredientResponse;
    }
}
