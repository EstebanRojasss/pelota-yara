package com.forum.api.infra.adapter.in.rest.dto;

import com.forum.api.domain.model.Equipo;

public record EquipoPartidoDto(Long id, String nombre, String logo) {

    public static EquipoPartidoDto fromDomain(Equipo equipo){
        return new EquipoPartidoDto(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getLogo()
        );
    }
}
