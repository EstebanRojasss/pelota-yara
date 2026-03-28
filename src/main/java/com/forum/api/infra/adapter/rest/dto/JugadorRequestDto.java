package com.forum.api.infra.adapter.rest.dto;

import com.forum.api.domain.Jugador;

public record JugadorRequestDto(String nombre, Integer edad, String nacionalidad, EquipoRequestDto equipo) {

    public Jugador toDomain(){
        return Jugador.create(
                nombre,
                edad,
                nacionalidad,
                equipo.toDomain()
        );
    }
}
