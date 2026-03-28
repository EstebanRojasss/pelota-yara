package com.forum.api.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Equipo {
    private Long id;
    private String nombre;
    private String apodo;
    private LocalDate fundacion;
    private Set<Competencias> competencias;

    private Equipo(Long id, String nombre, String apodo, LocalDate fundacion, Set<Competencias> competencias) {
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
        this.fundacion = fundacion;
        this.competencias = competencias;
    }

    public static Equipo restore(Long id, String nombre, String apodo, LocalDate fundacion, Set<Competencias> competencias){
        return new Equipo(id, nombre, apodo, fundacion, competencias);
    }

    public static Equipo create(String nombre, String apodo, LocalDate fundacion, Set<Competencias> competencias){
        return new Equipo(null, nombre, apodo, fundacion, competencias);
    }

    public Set<Competencias> getCompetencias() {
        return competencias;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(id, equipo.id) && Objects.equals(nombre, equipo.nombre) && Objects.equals(apodo, equipo.apodo) && Objects.equals(fundacion, equipo.fundacion) && Objects.equals(competencias, equipo.competencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apodo, fundacion, competencias);
    }
}
