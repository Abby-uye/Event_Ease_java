package com.beautyclan.eventease.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> alreadyExistException(AlreadyExistException alreadyExistException){
        Map<String, Object> response = new HashMap<>();
        response.put("data", alreadyExistException.getMessage());
        response.put("httpStatus", HttpStatus.CONFLICT);
        response.put("isSuccessful", false);
        response.put("statusCode", HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BeyondLimitException.class)
    public ResponseEntity<?> beyondLimitException(BeyondLimitException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("data", exception.getMessage());
        response.put("httpStatus", HttpStatus.BAD_REQUEST);
        response.put("isSuccessful", false);
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventException.class)
    public ResponseEntity<?> eventException(BeyondLimitException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("data", exception.getMessage());
        response.put("httpStatus", HttpStatus.BAD_REQUEST);
        response.put("isSuccessful", false);
        response.put("statusCode", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




}
