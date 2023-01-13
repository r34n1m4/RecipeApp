package com.reanima.business.mapper;

import com.reanima.business.model.IngredientDto;
import com.reanima.business.repository.model.IngredientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientDto entityToDto (IngredientEntity ingredientEntity);

    IngredientEntity dtoToEntity(IngredientDto ingredientDto);
}
