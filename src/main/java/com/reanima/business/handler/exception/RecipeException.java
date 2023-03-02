package com.reanima.business.handler.exception;

import javax.validation.ValidationException;

public class RecipeException extends ValidationException {

    public RecipeException(String message) {
        super(message);
    }
}
