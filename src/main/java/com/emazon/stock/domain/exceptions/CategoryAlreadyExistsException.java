package com.emazon.stock.domain.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String entity, String message) {
        super(entity + " '" + message + "' already exists");
    }
}
