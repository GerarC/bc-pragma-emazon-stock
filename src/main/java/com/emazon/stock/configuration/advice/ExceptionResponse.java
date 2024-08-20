package com.emazon.stock.configuration.advice;

import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private HttpStatusCode statusCode;
    private String message;
    private LocalDateTime timestamp;
}
