package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public String handleNumberFormat(ConstraintViolationException ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());
        log.error("Handling ConstraintViolationException: {}", ex.getMessage());
        return "400error";
    }

    // ExceptionHandler method for handling the default /error endpoint

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleDefaultError(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        log.error("Handling default error: {}", ex.getMessage());
        return "500error";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "Invalid input format: please enter a valid number.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
