package com.forum.api.infra.adapter.rest.dto;

import com.forum.api.domain.Jugador;

public record JugadorResponseDto(Long id, String nombre, Integer edad, String nacionalidad, EquipoResponseDto responseDto) {
    public static JugadorResponseDto fromDomain(Jugador jugador){
        return new JugadorResponseDto(
                jugador.getId(),
                jugador.getNombre(),
                jugador.getEdad(),
                jugador.getNacionalidad(),
                EquipoResponseDto.fromDomain(jugador.getEquipo())
        );
    }
}
