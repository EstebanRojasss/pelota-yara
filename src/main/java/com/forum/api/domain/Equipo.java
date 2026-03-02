package com.forum.api.domain;

import java.time.LocalDate;
import java.util.Set;

public class Equipo {
    private Long id;
    private String nombre;
    private String apodo;
    private LocalDate fundacion;

    private Set<Jugador> jugador;
    private Estadio estadio;


    public Equipo() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setFundacion(LocalDate fundacion) {
        this.fundacion = fundacion;
    }

    public void setJugador(Set<Jugador> jugador) {
        this.jugador = jugador;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public LocalDate getFundacion() {
        return fundacion;
    }

    public Set<Jugador> getJugador() {
        return jugador;
    }

    public Estadio getEstadio() {
        return estadio;
    }
}
