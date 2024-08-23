package com.emazon.stock.configuration.advice;

import com.emazon.stock.domain.exceptions.EntityAlreadyExistsException;
import com.emazon.stock.domain.exceptions.EmptyFieldException;
import com.emazon.stock.domain.exceptions.EntityNotFoundException;
import com.emazon.stock.domain.exceptions.OutOfBoundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        String message = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .message(message).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(OutOfBoundsException.class)
    public ResponseEntity<ExceptionResponse> handleOutOfBoundsException(OutOfBoundsException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryNameAlreadyExistsException(EntityAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .statusCode(HttpStatus.CONFLICT)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}
