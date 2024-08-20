package com.emazon.stock.domain.exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String message) {
        super("Empty '" + message + "' field not allowed");
    }
}
