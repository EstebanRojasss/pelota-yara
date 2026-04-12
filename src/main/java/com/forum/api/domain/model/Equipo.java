package com.forum.api.domain.model;

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

    public static Equipo restore(Long id, String nombre, String apodo, LocalDate fundacion, Set<Competencias> competencias) {
        return new Equipo(id, nombre, apodo, fundacion, competencias);
    }

    public static Equipo create(String nombre, String apodo, LocalDate fundacion, Set<Competencias> competencias) {
        return new Equipo(null, nombre, apodo, fundacion, competencias);
    }

    public Set<Competencias> getCompetencias() {
        return this.competencias;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApodo() {
        return this.apodo;
    }

    public LocalDate getFundacion() {
        return this.fundacion;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Equipo equipo = (Equipo)o;
        return Objects.equals(this.id, equipo.id) && Objects.equals(this.nombre, equipo.nombre) && Objects.equals(this.apodo, equipo.apodo) && Objects.equals(this.fundacion, equipo.fundacion) ;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.nombre, this.apodo, this.fundacion);
    }

    public String toString() {
        return "Equipo{id=" + this.id + ", nombre='" + this.nombre + "', apodo='" + this.apodo + "', fundacion=" + String.valueOf(this.fundacion) + ", competencias=" + String.valueOf(this.competencias) + "}";
    }
}

