package com.forum.api.domain;

public class Jugador {
    private Long id;
    private String nombre;
    private Integer edad;
    private String nacionalidad;

    private Equipo equipo;

    private Jugador(Long id, String nombre, Integer edad, String nacionalidad, Equipo equipo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.equipo = equipo;
    }


    public static Jugador create(String nombre, Integer edad, String nacionalidad, Equipo equipo){
        return new Jugador(null, nombre, edad, nacionalidad, equipo);
    }

    public static Jugador restore(Long id, String nombre, Integer edad, String nacionalidad, Equipo equipo){
        return new Jugador(id, nombre, edad, nacionalidad, equipo);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public Equipo getEquipo() {
        return equipo;
    }
}
