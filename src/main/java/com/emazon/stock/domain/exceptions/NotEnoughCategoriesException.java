package com.emazon.stock.domain.exceptions;

public class NotEnoughCategoriesException extends RuntimeException {
    public NotEnoughCategoriesException(String message) {
        super(message);
    }
}
