package com.forum.api.application.in.dto;

public record JugadorDataDto(
        Long id,
        String nombre,
        Integer edad,
        TeamDataDto equipoDto
) {

    public static JugadorDataDto map(Long id, String nombre, Integer edad, TeamDataDto equipoDto){
        return new JugadorDataDto(id, nombre, edad, equipoDto);
    }
}
