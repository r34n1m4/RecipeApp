package com.reanima.business.mapper;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.repository.model.RecipeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.reanima.util.Util.recipeEntity;
import static com.reanima.util.Util.recipeResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RecipeMapperTest {

    @Autowired
    private RecipeMapper recipeMapper;

    @Test
    void entityToDto_receiveEntity_returnResponse() {
        RecipeEntity recipeEntity = recipeEntity();
        RecipeDto expected = recipeResponse(recipeEntity);

        RecipeDto result = recipeMapper.entityToDto(recipeEntity);

        assertEquals(expected, result);
    }
}