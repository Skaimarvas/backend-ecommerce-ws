package com.example.becommercews.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BecommerceErrorResponse> handleException(BecommerceException becommerceException){
        BecommerceErrorResponse errorResponse = new BecommerceErrorResponse(becommerceException.getHttpStatus().value(), becommerceException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,becommerceException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BecommerceErrorResponse> handleException(Exception exception){
        BecommerceErrorResponse errorResponse = new BecommerceErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
    }
}
