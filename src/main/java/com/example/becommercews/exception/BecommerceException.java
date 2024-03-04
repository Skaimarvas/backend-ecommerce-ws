package com.example.becommercews.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class BecommerceException extends RuntimeException{
    private HttpStatus httpStatus;

    public BecommerceException(String message,HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
