package com.forum.api.infra.adapter.in.rest.dto;


import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;

public record PartidoResponseDto(Long id, StatusPartido status,EquipoPartidoDto equipoLocal, EquipoPartidoDto equipoVisitante, Integer golVisitante, Integer golLocal, Integer minutoActual, Integer minutoAdicional1T, Integer minutoAdicional2T,
                                 LigaResponseDto liga) {

    public static PartidoResponseDto fromDomainExistent(Partido partido) {
        return new PartidoResponseDto(partido.getId(),
                partido.getStatus(),
                EquipoPartidoDto.fromDomain(partido.getEquipoLocal()),
                EquipoPartidoDto.fromDomain(partido.getEquipoVisitante()),
                partido.getGolVisitante(),
                partido.getGolLocal(),
                partido.getMinutoActual(),
                partido.getMinutoAdicional1T(),
                partido.getMinutoAdicional2T(),
                LigaResponseDto.fromDomain(partido.getLiga()));
    }
}

