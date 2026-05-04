package com.forum.api.infra.adapter.in.rest.dto;

import com.forum.api.domain.model.Liga;

public record LigaResponseDto(
        Long id,
        String nombre,
        String pais
) {

    public static LigaResponseDto fromDomain(Liga liga){
        return new LigaResponseDto(
                liga.getId(),
                liga.getNombre(),
                liga.getPais()
        );
    }
}
