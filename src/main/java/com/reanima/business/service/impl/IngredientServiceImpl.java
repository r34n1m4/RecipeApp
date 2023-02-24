package com.reanima.business.service.impl;

import com.reanima.business.handler.exception.IngredientException;
import com.reanima.business.mapper.IngredientMapper;
import com.reanima.business.model.IngredientDto;
import com.reanima.business.repository.IngredientRepository;
import com.reanima.business.repository.model.IngredientEntity;
import com.reanima.business.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.reanima.business.util.LogMessages.INGREDIENT_WITH_THIS_NAME_ALREADY_EXIST;

@Component
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientMapper ingredientMapper;

    @Override
    public List<IngredientDto> findAllIngredients() {
        List<IngredientEntity> ingredientEntity = ingredientRepository.findAll();
        return ingredientEntity.stream().map(ingredientMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<IngredientDto> findIngredientById(int ingredientId) {
        Optional<IngredientEntity> ingredientEntity = ingredientRepository.findById(ingredientId);
        return ingredientEntity.map(ingredientMapper::entityToDto);
    }

    @Override
    public IngredientDto saveIngredient(IngredientDto ingredientDto) throws IngredientException {
        if (IngredientNameMatch(ingredientDto)) {
            throw new IngredientException(INGREDIENT_WITH_THIS_NAME_ALREADY_EXIST);
        }
        IngredientEntity ingredientEntity = ingredientRepository.save(ingredientMapper.dtoToEntity(ingredientDto));
        ingredientMapper.entityToDto(ingredientEntity);
        return ingredientDto;
    }

    @Override
    public void updateIngredient(IngredientDto ingredientDto) {
        IngredientEntity ingredientEntity = ingredientRepository.save(ingredientMapper.dtoToEntity(ingredientDto));
        ingredientMapper.entityToDto(ingredientEntity);
    }

    @Override
    public void deleteIngredientById(int ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }

    public boolean IngredientNameMatch(IngredientDto ingredientDto) {
        return ingredientRepository.findAll().stream()
                .anyMatch(e -> e.getIngredientName()
                        .equalsIgnoreCase(ingredientDto
                                .getIngredientName()));
    }
}
