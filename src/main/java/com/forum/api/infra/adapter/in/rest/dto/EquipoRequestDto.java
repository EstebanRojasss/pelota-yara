package com.forum.api.infra.adapter.in.rest.dto;

import com.forum.api.domain.model.Equipo;

import java.time.LocalDate;

public record EquipoRequestDto(Long id, String nombre, String apodo, Integer fundacion, Long equipoFixtureId, String logo) {
    public Equipo toDomain() {
        return Equipo.create(nombre,
                apodo,
                fundacion,
                equipoFixtureId,
                logo);
    }

    public Equipo toDomainExistent() {
        return Equipo.restore(id,
                nombre,
                apodo,
                fundacion,
                equipoFixtureId,
                logo);
    }

}

