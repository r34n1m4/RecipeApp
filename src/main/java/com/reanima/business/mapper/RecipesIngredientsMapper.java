package com.reanima.business.mapper;

import com.reanima.business.model.RecipesIngredientsDto;
import com.reanima.business.repository.model.RecipesIngredientsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipesIngredientsMapper {

    RecipesIngredientsDto entityToDto (RecipesIngredientsEntity recipesIngredientsEntity);

    RecipesIngredientsEntity dtoToEntity(RecipesIngredientsDto recipesIngredientsDto);

}
