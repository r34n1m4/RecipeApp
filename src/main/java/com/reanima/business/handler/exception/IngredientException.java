package com.reanima.business.handler.exception;


import javax.validation.ValidationException;

public class IngredientException extends ValidationException {

    public IngredientException(String message) {
        super(message);
    }
}
