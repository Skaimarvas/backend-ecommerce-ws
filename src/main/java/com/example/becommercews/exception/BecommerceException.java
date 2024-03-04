package com.example.becommercews.exception;

import org.springframework.http.HttpStatus;

public class BecommerceException extends RuntimeException{
    private HttpStatus httpStatus;

    public BecommerceException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
