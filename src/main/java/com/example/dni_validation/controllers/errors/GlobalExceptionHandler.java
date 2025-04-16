package com.example.dni_validation.controllers.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for controller layer.
 * <p>
 * This class uses the @ControllerAdvice annotation to handle exceptions thrown by
 * controller methods across the whole application. It centralizes error handling logic,
 * especially for validation errors triggered by annotated constraints (e.g., @NotNull, @Email).
 * <p>
 * Currently, it handles:
 * - MethodArgumentNotValidException: triggered when request body validation fails.
 * <p>
 * Each exception handler method returns a meaningful HTTP response with error details
 * to improve API usability and client feedback.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions thrown when a request contains invalid data
     * based on constraint annotations (e.g., @NotNull, @Email) in controller DTOs.
     *
     * @param ex the exception containing validation errors
     * @return a 400 Bad Request response with a map of field names and corresponding error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.badRequest().body(errors);
    }

}
