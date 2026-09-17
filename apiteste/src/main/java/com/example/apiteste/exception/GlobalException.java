package com.example.apiteste.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ExceptionValidation.class)
    public ResponseEntity<Map<String, Object>> produtonoeconcotrado(
            ExceptionValidation ex) {
        Map<String, Object> corpo = new HashMap<>();
        corpo.put("status", HttpStatus.NOT_FOUND.value());
        corpo.put("mensagem", ex.getMessage());
        corpo.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(corpo, HttpStatus.NOT_FOUND);
    }
}
