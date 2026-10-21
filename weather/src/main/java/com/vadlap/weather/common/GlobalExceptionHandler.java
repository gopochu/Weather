package com.vadlap.weather.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExistsException e) {
        ApiError error = new ApiError("USER_ALREADY_EXIST", e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
