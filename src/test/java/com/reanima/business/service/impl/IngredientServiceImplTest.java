package com.reanima.business.service.impl;

import com.reanima.business.handler.exception.IngredientException;
import com.reanima.business.mapper.IngredientMapper;
import com.reanima.business.model.IngredientDto;
import com.reanima.business.repository.IngredientRepository;
import com.reanima.business.repository.model.IngredientEntity;
import com.reanima.business.repository.model.RecipesIngredientsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.reanima.business.util.LogMessages.INGREDIENT_WITH_ID_NOT_FOUND;
import static com.reanima.util.CommonUtil.*;
import static com.reanima.util.IngredientUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private IngredientMapper ingredientMapper;
    private IngredientEntity ingredientEntity;
    private IngredientDto ingredientDto;
    private List<IngredientEntity> ingredientEntityList;

    private Set<RecipesIngredientsEntity> recipesIngredients = new HashSet<>();

    @BeforeEach
    void setUp() {

        ingredientDto = new IngredientDto(
                VALID_ID,
                INGREDIENT_NAME,
                INGREDIENT_DESCRIPTION,
                INGREDIENT_TYPE,
                CALORIES,
                CARBOHYDRATES,
                FAT,
                PROTEIN,
                WATER,
                CHOLESTEROL,
                LOCAL_DATE_TIME
        );

        ingredientEntity = new IngredientEntity(
                VALID_ID,
                INGREDIENT_NAME,
                INGREDIENT_DESCRIPTION,
                INGREDIENT_TYPE,
                CALORIES,
                CARBOHYDRATES,
                FAT,
                PROTEIN,
                WATER,
                CHOLESTEROL,
                LOCAL_DATE_TIME,
                recipesIngredients
        );

        ingredientEntityList = new ArrayList<>(List.of(ingredientEntity));
    }

    @Test
    @DisplayName("Test: Find all Ingredients")
    void testFindAllIngredients() {
        when(ingredientRepository.findAll()).thenReturn(ingredientEntityList);
        when(ingredientMapper.entityToDto(ingredientEntity)).thenReturn(ingredientDto);
        List<IngredientDto> ingredientDtoList = ingredientService.findAllIngredients();
        assertEquals(1, ingredientDtoList.size());
        assertEquals(1, ingredientDtoList.get(0).getIngredientId());
        assertEquals(INGREDIENT_NAME, ingredientDtoList.get(0).getIngredientName());
    }

    @Test
    @DisplayName("Test: Find Ingredient by valid ID")
    void testFindIngredientById_ValidId() throws NoSuchElementException {
        when(ingredientRepository.findById(VALID_ID)).thenReturn(Optional.of(ingredientEntity));
        when(ingredientMapper.entityToDto(ingredientEntity)).thenReturn(ingredientDto);
        Optional<IngredientDto> returnedIngredient = ingredientService.findIngredientById(ingredientDto.getIngredientId());
        assertEquals(ingredientDto.getIngredientId(), returnedIngredient.get().getIngredientId());
        assertEquals(ingredientDto.getIngredientName(), returnedIngredient.get().getIngredientName());
        assertEquals(ingredientDto.getIngredientDescription(), returnedIngredient.get().getIngredientDescription());
        assertEquals(ingredientDto.getIngredientType(), returnedIngredient.get().getIngredientType());
        assertEquals(ingredientDto.getCalories(), returnedIngredient.get().getCalories());
        assertEquals(ingredientDto.getCarbohydrates(), returnedIngredient.get().getCarbohydrates());
        assertEquals(ingredientDto.getFat(), returnedIngredient.get().getFat());
        assertEquals(ingredientDto.getProtein(), returnedIngredient.get().getProtein());
        assertEquals(ingredientDto.getWater(), returnedIngredient.get().getWater());
        assertEquals(ingredientDto.getCholesterol(), returnedIngredient.get().getCholesterol());
        verify(ingredientRepository, times(1)).findById(VALID_ID);
    }

    @Test
    @DisplayName("Test: Find Ingredient by invalid ID")
    void testFindIngredientById_InvalidId() {
        when(ingredientRepository.findById(INVALID_ID)).thenReturn(Optional.empty());
        assertFalse(ingredientService.findIngredientById(INVALID_ID).isPresent());
        verify(ingredientRepository, times(1)).findById(INVALID_ID);
    }

    @Test
    @DisplayName("Test: Save Ingredient")
    void testSaveIngredient() {
        when(ingredientRepository.save(ingredientEntity)).thenReturn(ingredientEntity);
        when(ingredientMapper.entityToDto(ingredientEntity)).thenReturn(ingredientDto);
        when(ingredientMapper.dtoToEntity(ingredientDto)).thenReturn(ingredientEntity);
        IngredientDto ingredientSaved = ingredientService.saveIngredient(ingredientDto);
        assertFalse(ingredientService.IngredientNameMatch(ingredientSaved));
        assertEquals(ingredientDto, ingredientSaved);
        verify(ingredientRepository, times(1)).save(ingredientEntity);
    }

    @Test
    @DisplayName("Test: Save Ingredient when already exist")
    void testSaveIngredient_WhenAlreadyExist() {
        when(ingredientService.IngredientNameMatch(ingredientDto))
                .thenThrow(new IngredientException("Ingredient with this name already exist."));
        Assertions.assertThrows(IngredientException.class, () -> ingredientService.IngredientNameMatch(ingredientDto));
    }

    @Test
    @DisplayName("Test: Update Ingredient")
    void testUpdateIngredient() {
        when(ingredientRepository.save(ingredientEntity)).thenReturn(ingredientEntity);
        when(ingredientMapper.entityToDto(ingredientEntity)).thenReturn(ingredientDto);
        when(ingredientMapper.dtoToEntity(ingredientDto)).thenReturn(ingredientEntity);
        IngredientDto ingredientUpdated = ingredientService.saveIngredient(ingredientDto);
        assertFalse(ingredientService.IngredientNameMatch(ingredientUpdated));
        assertEquals(ingredientDto, ingredientUpdated);
        verify(ingredientRepository, times(1)).save(ingredientEntity);
    }

    @Test
    @DisplayName("Test: Delete Ingredient by valid ID")
    void testDeleteIngredientById_ValidId() {
        ingredientService.deleteIngredientById(ingredientDto.getIngredientId());
        verify(ingredientRepository, times(1)).deleteById(ingredientDto.getIngredientId());
    }

    @Test
    @DisplayName("Test: Delete Ingredient by invalid ID")
    void testDeleteIngredientById_InvalidId() {
        lenient().doThrow(new IngredientException(INGREDIENT_WITH_ID_NOT_FOUND))
                .when(ingredientRepository).deleteById(INVALID_ID);
        Assertions.assertThrows(IngredientException.class, () -> ingredientService.deleteIngredientById(INVALID_ID));
        verify(ingredientRepository, times(1)).deleteById(INVALID_ID);
    }

    @Test
    @DisplayName("Test: Ingredient name match when found")
    void testIngredientNameMatch_Found() {
        when(ingredientRepository.findAll()).thenReturn(ingredientEntityList);
        assertTrue(ingredientService.IngredientNameMatch(ingredientDto));
    }

    @Test
    @DisplayName("Test: Ingredient name match when not found")
    void testIngredientNameMatch_NotFound() {
        ingredientDto.setIngredientName("Random Name");
        when(ingredientRepository.findAll()).thenReturn(ingredientEntityList);
        assertFalse(ingredientService.IngredientNameMatch(ingredientDto));
    }
}