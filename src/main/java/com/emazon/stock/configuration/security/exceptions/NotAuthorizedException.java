package com.emazon.stock.configuration.security.exceptions;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }

}
