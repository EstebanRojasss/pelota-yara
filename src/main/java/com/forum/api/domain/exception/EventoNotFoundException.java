package com.forum.api.domain.exception;

public class EventoNotFoundException extends RuntimeException{
    public EventoNotFoundException(String message) {
        super(message);
    }
}
