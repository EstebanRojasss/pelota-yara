package com.forum.api.domain.model;

public enum EventoPartido {
    GOL(30),
    TARGETA_AMARILLA(10),
    TARGETA_ROJA(5),
    FALTA(40),
    SUSTITUCION(5);

    private final Integer probabilidad;

    EventoPartido(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Integer getProbabilidad() {
        return this.probabilidad;
    }
}

