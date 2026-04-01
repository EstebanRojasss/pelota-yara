package com.forum.api.infra.adapter.rest.dto;

import com.forum.api.domain.model.Competencias;
import com.forum.api.domain.model.Equipo;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


public record EquipoRequestDto(String nombre, String apodo, LocalDate fundacion, Set<String> competencias) {

    public Equipo toDomain(){
        return Equipo.create(nombre,
                apodo,
                fundacion,
                competencias.stream()
                        .map(Competencias::fromValor)
                        .collect(Collectors.toSet())
                );
    }
}
