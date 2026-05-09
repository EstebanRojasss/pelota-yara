package com.forum.api.infra.adapter.in.rest.dto;

import com.forum.api.domain.model.Jugador;


public record JugadorRequestDto(String nombre, Integer edad, String nacionalidad, EquipoRequestDto equipo, Long fixtureId) {
    public Jugador toDomain() {
        return Jugador.create(nombre,
                edad,
                fixtureId,
                equipo.toDomain());
    }
}

