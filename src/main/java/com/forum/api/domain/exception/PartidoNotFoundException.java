package com.forum.api.domain.exception;

public class PartidoNotFoundException extends RuntimeException {
    public PartidoNotFoundException(String message) {
        super(message);
    }
}
