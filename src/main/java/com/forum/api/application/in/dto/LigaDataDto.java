package com.forum.api.application.in.dto;

public record LigaDataDto(
        Long id,
        String nombre,
        String pais,
        Integer temporada
) {
}
