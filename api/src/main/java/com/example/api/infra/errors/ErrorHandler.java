package com.example.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().map(ValidationError::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record ValidationError(String field, String error){
        public ValidationError(FieldError e){
            this(e.getField(), e.getDefaultMessage());
        }
    }
}
