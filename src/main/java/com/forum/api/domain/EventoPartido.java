package com.forum.api.domain;

public enum EventoPartido {
    GOL(30),
    TARGETA_AMARILLA(10),
    TARGETA_ROJA(5),
    FALTA(40),
    SUSTITUCION(5),
    FIN_PARTIDO(0),
    INICIO_DEL_SEGUNDO_TIEMPO(0),
    DESCANSO(0),
    MINUTOS_ADICIONADOS(0);

    private final Integer probabilidad;

    EventoPartido(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Integer getProbabilidad() {
        return probabilidad;
    }
}
