package com.urlol.backend.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String errorMessage;
    private int statusCode;
    private LocalDateTime timestamp;
}
