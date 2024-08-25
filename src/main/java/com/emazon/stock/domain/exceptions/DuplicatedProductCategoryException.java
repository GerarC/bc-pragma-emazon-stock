package com.emazon.stock.domain.exceptions;

public class DuplicatedProductCategoryException extends RuntimeException {
    public DuplicatedProductCategoryException(String message) {
        super(message);
    }
}
