package com.reanima.business.mapper;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.repository.model.RecipeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-08T21:32:29+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.14.1 (JetBrains s.r.o.)"
)
@Component
public class RecipeMapperImpl implements RecipeMapper {

    @Override
    public RecipeDto entityToDto(RecipeEntity recipeEntity) {
        if ( recipeEntity == null ) {
            return null;
        }

        RecipeDto recipeDto = new RecipeDto();

        recipeDto.setRecipeId( recipeEntity.getRecipeId() );
        recipeDto.setRecipeName( recipeEntity.getRecipeName() );
        recipeDto.setRecipeDescription( recipeEntity.getRecipeDescription() );
        recipeDto.setRecipePreparation( recipeEntity.getRecipePreparation() );
        recipeDto.setCuisineType( recipeEntity.getCuisineType() );
        recipeDto.setDishType( recipeEntity.getDishType() );
        recipeDto.setRecipeCreated( recipeEntity.getRecipeCreated() );

        return recipeDto;
    }

    @Override
    public RecipeEntity dtoToEntity(RecipeDto recipeDto) {
        if ( recipeDto == null ) {
            return null;
        }

        RecipeEntity recipeEntity = new RecipeEntity();

        recipeEntity.setRecipeId( recipeDto.getRecipeId() );
        recipeEntity.setRecipeName( recipeDto.getRecipeName() );
        recipeEntity.setRecipeDescription( recipeDto.getRecipeDescription() );
        recipeEntity.setRecipePreparation( recipeDto.getRecipePreparation() );
        recipeEntity.setCuisineType( recipeDto.getCuisineType() );
        recipeEntity.setDishType( recipeDto.getDishType() );
        recipeEntity.setRecipeCreated( recipeDto.getRecipeCreated() );

        return recipeEntity;
    }
}
