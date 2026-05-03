package com.forum.api.domain.model;

import java.time.LocalDate;

public class Estadio {
    private Long id;
    private String nombre;
    private Integer capacidad;
    private LocalDate fundacion;
    private String direccion;
    private String ciudad;
    private Long equipoId;

    public Estadio(Long id, String nombre, Integer capacidad, LocalDate fundacion, String direccion, String ciudad, Long equipoId) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.fundacion = fundacion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.equipoId = equipoId;
    }
}

