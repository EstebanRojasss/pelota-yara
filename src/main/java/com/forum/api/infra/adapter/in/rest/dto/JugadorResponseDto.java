package com.forum.api.infra.adapter.in.rest.dto;

import com.forum.api.domain.model.Jugador;

public record JugadorResponseDto(Long id, String nombre, Integer edad, EquipoResponseDto responseDto) {
    public static JugadorResponseDto fromDomain(Jugador jugador) {
        return new JugadorResponseDto(jugador.getId(),
                jugador.getNombre(),
                jugador.getEdad(),
                EquipoResponseDto.fromDomain(jugador.getEquipo()));
    }

}

