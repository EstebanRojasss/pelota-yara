package com.forum.api.infra.adapter.in.rest.dto;

import com.forum.api.domain.model.Equipo;

import java.time.LocalDate;


public record EquipoResponseDto(Long id, String nombre, String pais, Integer fundacion) {
    public static EquipoResponseDto fromDomain(Equipo equipo) {
        return new EquipoResponseDto(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getPais(),
                equipo.getFundacion());
    }
}

