package com.forum.api.domain.model;


import java.util.Objects;


public class Equipo {
    private Long id;
    private String nombre;
    private String pais;
    private Integer fundacion;
    private Long equipoFixtureId;
    private String logo;

    private Equipo(Long id, String nombre, String pais, Integer fundacion, Long equipoFixtureId, String logo) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fundacion = fundacion;
        this.equipoFixtureId = equipoFixtureId;
        this.logo = logo;
    }

    public static Equipo restore(Long id, String nombre, String pais, Integer fundacion, Long equipoFixtureId, String logo) {
        return new Equipo(id, nombre, pais, fundacion,equipoFixtureId, logo);
    }

    public static Equipo create(String nombre, String pais, Integer fundacion, Long equipoFixtureId, String logo) {
        return new Equipo(null, nombre, pais, fundacion, equipoFixtureId, logo);
    }

    public void actualizar(String nombre,
                           String pais,
                           Integer fundacion,
                           String logo){
        this.nombre = nombre;
        this.pais = pais;
        this.fundacion = fundacion;
        this.logo = logo;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public Integer getFundacion() {
        return fundacion;
    }

    public Long getEquipoFixtureId() {
        return equipoFixtureId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Equipo equipo = (Equipo)o;
        return Objects.equals(this.id, equipo.id) && Objects.equals(this.nombre, equipo.nombre) && Objects.equals(this.pais, equipo.pais) && Objects.equals(this.fundacion, equipo.fundacion) ;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.nombre, this.pais, this.fundacion);
    }

    public String toString() {
        return "Equipo{id=" + this.id + ", nombre='" + this.nombre + "', pais='" + this.pais + "', fundacion=" + this.fundacion+ "}";
    }
}

