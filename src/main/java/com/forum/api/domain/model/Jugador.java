package com.forum.api.domain.model;

public class Jugador {
    private final Long id;
    private String nombre;
    private Integer edad;
    private Long fixtureId;
    private Equipo equipo;

    private Jugador(Long id, String nombre, Integer edad,Long fixtureId, Equipo equipo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fixtureId = fixtureId;
        this.equipo = equipo;
    }

    public static Jugador create(String nombre, Integer edad,Long fixtureId, Equipo equipo) {
        return new Jugador(null,
                nombre,
                edad,
                fixtureId,
                equipo);
    }

    public static Jugador restore(Long id, String nombre, Integer edad, Long fixtureId, Equipo equipo) {
        return new Jugador(id,
                nombre,
                edad,
                fixtureId,
                equipo);
    }

    public void actualizar(String nombre, Integer edad, Equipo equipo){
        this.nombre = nombre;
        this.edad = edad;
        this.equipo = equipo;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Integer getEdad() {
        return this.edad;
    }


    public Equipo getEquipo() {
        return this.equipo;
    }

    public Long getFixtureId() {
        return fixtureId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}

