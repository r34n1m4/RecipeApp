package com.reanima.business.mapper;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.repository.model.IngredientEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-24T15:08:26+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.14.1 (JetBrains s.r.o.)"
)
@Component
public class IngredientMapperImpl implements IngredientMapper {

    @Override
    public IngredientDto entityToDto(IngredientEntity ingredientEntity) {
        if ( ingredientEntity == null ) {
            return null;
        }

        IngredientDto ingredientDto = new IngredientDto();

        ingredientDto.setIngredientId( ingredientEntity.getIngredientId() );
        ingredientDto.setIngredientName( ingredientEntity.getIngredientName() );
        ingredientDto.setIngredientDescription( ingredientEntity.getIngredientDescription() );
        ingredientDto.setIngredientType( ingredientEntity.getIngredientType() );
        ingredientDto.setCalories( ingredientEntity.getCalories() );
        ingredientDto.setCarbohydrates( ingredientEntity.getCarbohydrates() );
        ingredientDto.setFat( ingredientEntity.getFat() );
        ingredientDto.setProtein( ingredientEntity.getProtein() );
        ingredientDto.setWater( ingredientEntity.getWater() );
        ingredientDto.setCholesterol( ingredientEntity.getCholesterol() );
        ingredientDto.setIngredientCreated( ingredientEntity.getIngredientCreated() );

        return ingredientDto;
    }

    @Override
    public IngredientEntity dtoToEntity(IngredientDto ingredientDto) {
        if ( ingredientDto == null ) {
            return null;
        }

        IngredientEntity ingredientEntity = new IngredientEntity();

        ingredientEntity.setIngredientId( ingredientDto.getIngredientId() );
        ingredientEntity.setIngredientName( ingredientDto.getIngredientName() );
        ingredientEntity.setIngredientDescription( ingredientDto.getIngredientDescription() );
        ingredientEntity.setIngredientType( ingredientDto.getIngredientType() );
        ingredientEntity.setCalories( ingredientDto.getCalories() );
        ingredientEntity.setCarbohydrates( ingredientDto.getCarbohydrates() );
        ingredientEntity.setFat( ingredientDto.getFat() );
        ingredientEntity.setProtein( ingredientDto.getProtein() );
        ingredientEntity.setWater( ingredientDto.getWater() );
        ingredientEntity.setCholesterol( ingredientDto.getCholesterol() );
        ingredientEntity.setIngredientCreated( ingredientDto.getIngredientCreated() );

        return ingredientEntity;
    }
}
