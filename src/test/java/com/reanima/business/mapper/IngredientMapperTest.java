package com.reanima.business.mapper;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.repository.model.IngredientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reanima.util.IngredientUtil.ingredientEntity;
import static com.reanima.util.IngredientUtil.ingredientResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IngredientMapperTest {

    @Autowired
    private IngredientMapper ingredientMapper;

    @Test
    @DisplayName("Test: Entity to DTO equality")
    void entityToDto_receiveEntity_returnResponse() {
        IngredientEntity ingredientEntity = ingredientEntity();
        IngredientDto expected = ingredientResponse(ingredientEntity);

        IngredientDto result = ingredientMapper.entityToDto(ingredientEntity);

        assertEquals(expected, result);
    }

}