package com.forum.api.application.in.dto;

public record LigaDataDto(
        Long id,
        String nombre,
        String pais,
        Integer temporada
) {

    public static LigaDataDto map(Long id, String nombre, String pais, Integer temporada){
        return new LigaDataDto(
                id,
                nombre,
                pais,
                temporada);
    }
}
