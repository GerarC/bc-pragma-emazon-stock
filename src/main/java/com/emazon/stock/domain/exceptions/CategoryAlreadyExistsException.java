package com.emazon.stock.domain.exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message) {
        super("Category '" + message + "' already exists");
    }
}
