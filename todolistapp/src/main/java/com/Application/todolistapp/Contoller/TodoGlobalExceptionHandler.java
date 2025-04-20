package com.Application.todolistapp.Contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class TodoGlobalExceptionHandler {


    @ExceptionHandler( IOException.class)
    public ResponseEntity<?> todocontrollerexceptionHandler(IOException e){
        System.out.println("this is form global exception handler");
        ResponseEntity<?> response = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       return response;
    }
}
