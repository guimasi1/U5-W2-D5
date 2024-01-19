package com.example.U5W2D5.exceptions;


public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
