package com.forum.api.domain.model;

import java.util.Set;

public class Liga {
    private final Long id;
    private String nombre;
    private String pais;
    private final Long fixtureLigaId;
    private Integer temporada;

    public Liga(Long id, String nombre, String pais, Long fixtureLigaId, Integer temporada) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fixtureLigaId = fixtureLigaId;
        this.temporada = temporada;
    }


    public Long getFixtureLigaId() {
        return fixtureLigaId;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getTemporada() {
        return temporada;
    }

}
