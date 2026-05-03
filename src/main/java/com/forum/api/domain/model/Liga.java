package com.forum.api.domain.model;

import java.util.Set;

public class Liga {
    private final Long id;
    private String nombre;
    private String pais;
    private Set<Equipo> equipos;
    private final Long fixtureLigaId;
    private Integer temporada;

    public Liga(Long id, String nombre, String pais, Set<Equipo> equipos, Long fixtureLigaId, Integer temporada) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.equipos = equipos;
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

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(Set<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getTemporada() {
        return temporada;
    }

}
