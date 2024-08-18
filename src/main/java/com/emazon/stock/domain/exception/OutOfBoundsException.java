package com.emazon.stock.domain.exception;

public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException(String message) {
        super(message);
    }
}
