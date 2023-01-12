package com.reanima.business.mapper;

import com.reanima.business.model.RecipeDto;
import com.reanima.business.repository.model.RecipeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeDto entityToDto (RecipeEntity recipeEntity);

    RecipeEntity dtoToEntity(RecipeDto recipeDto);
}
