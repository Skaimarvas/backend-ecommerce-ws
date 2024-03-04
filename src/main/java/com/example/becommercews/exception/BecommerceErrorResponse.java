package com.example.becommercews.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class BecommerceErrorResponse {
    private int status;
    private String message;
    private LocalDateTime dateTime;
}
