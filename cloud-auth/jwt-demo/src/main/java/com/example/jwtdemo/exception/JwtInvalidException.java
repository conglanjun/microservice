package com.example.jwtdemo.exception;

public class JwtInvalidException extends RuntimeException {
    public JwtInvalidException(String message) {
        super(message);
    }
}
