package com.urlol.backend.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.urlol.backend.model.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUrlNotFoundException(UrlNotFoundException ex) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    
}
