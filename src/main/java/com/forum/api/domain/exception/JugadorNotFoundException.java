package com.forum.api.domain.exception;

public class JugadorNotFoundException extends RuntimeException {
    public JugadorNotFoundException(String message) {
        super(message);
    }
}
