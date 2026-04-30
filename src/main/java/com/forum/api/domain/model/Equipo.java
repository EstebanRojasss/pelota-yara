package com.forum.api.domain.model;

import java.time.LocalDate;
import java.util.Objects;


public class Equipo {
    private Long id;
    private String nombre;
    private String apodo;
    private LocalDate fundacion;
    private Long equipoFixtureId;
    private String logo;

    private Equipo(Long id, String nombre, String apodo, LocalDate fundacion, Long equipoFixtureId, String logo) {
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
        this.fundacion = fundacion;
        this.equipoFixtureId = equipoFixtureId;
        this.logo = logo;
    }

    public static Equipo restore(Long id, String nombre, String apodo, LocalDate fundacion, Long equipoFixtureId, String logo) {
        return new Equipo(id, nombre, apodo, fundacion,equipoFixtureId, logo);
    }

    public static Equipo create(String nombre, String apodo, LocalDate fundacion, Long equipoFixtureId, String logo) {
        return new Equipo(null, nombre, apodo, fundacion, equipoFixtureId, logo);
    }


    public String getLogo() {
        return logo;
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

    public Long getEquipoFixtureId() {
        return equipoFixtureId;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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
        return "Equipo{id=" + this.id + ", nombre='" + this.nombre + "', apodo='" + this.apodo + "', fundacion=" + this.fundacion+ "}";
    }
}

