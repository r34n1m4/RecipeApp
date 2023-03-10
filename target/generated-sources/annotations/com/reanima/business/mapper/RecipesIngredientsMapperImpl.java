package com.reanima.business.mapper;

import com.reanima.business.model.RecipesIngredientsDto;
import com.reanima.business.repository.model.RecipesIngredientsEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-10T10:32:04+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.14.1 (JetBrains s.r.o.)"
)
@Component
public class RecipesIngredientsMapperImpl implements RecipesIngredientsMapper {

    @Override
    public RecipesIngredientsDto entityToDto(RecipesIngredientsEntity recipesIngredientsEntity) {
        if ( recipesIngredientsEntity == null ) {
            return null;
        }

        RecipesIngredientsDto recipesIngredientsDto = new RecipesIngredientsDto();

        recipesIngredientsDto.setRecipesIngredientsId( recipesIngredientsEntity.getRecipesIngredientsId() );
        recipesIngredientsDto.setQuantity( recipesIngredientsEntity.getQuantity() );

        return recipesIngredientsDto;
    }

    @Override
    public RecipesIngredientsEntity dtoToEntity(RecipesIngredientsDto recipesIngredientsDto) {
        if ( recipesIngredientsDto == null ) {
            return null;
        }

        RecipesIngredientsEntity recipesIngredientsEntity = new RecipesIngredientsEntity();

        recipesIngredientsEntity.setRecipesIngredientsId( recipesIngredientsDto.getRecipesIngredientsId() );
        recipesIngredientsEntity.setQuantity( recipesIngredientsDto.getQuantity() );

        return recipesIngredientsEntity;
    }
}
