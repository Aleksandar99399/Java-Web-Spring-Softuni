package com.softuni.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StudentHandler {



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<Error> handleHelloException(){
        return ResponseEntity.ok(new Error().setErrorName("error name").setDescription("Error description"));
    }
}
