package com.forum.api.infra.adapter.rest.dto;

import com.forum.api.domain.Competencias;
import com.forum.api.domain.Equipo;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record EquipoResponseDto(Long id, String nombre, String apodo, LocalDate fundacion, Set<String> competencias) {
    public static EquipoResponseDto fromDomain(Equipo equipo){
        return new EquipoResponseDto(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getApodo(),
                equipo.getFundacion(),
                equipo.getCompetencias().stream()
                        .map(Competencias::getValor)
                        .collect(Collectors.toSet()));
    }
}
