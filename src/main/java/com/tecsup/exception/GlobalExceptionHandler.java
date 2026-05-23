package com.tecsup.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> notFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<Map<String, String>> stock(StockInsuficienteException ex) {
        return ResponseEntity.status(400).body(Map.of("mensaje", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validacion(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(400).body(Map.of("mensaje", error));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> general(Exception ex) {
        return ResponseEntity.status(500).body(Map.of("mensaje", "Error interno: " + ex.getMessage()));
    }
}