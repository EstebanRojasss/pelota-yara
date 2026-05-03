package com.forum.api.application.in.dto;

public record VenueDataDto(
        Long id,
        String nombre,
        String ciudad,
        Integer capacidad
) {

    public static VenueDataDto map(Long id, String nombre, String ciudad, Integer capacidad){
        return new VenueDataDto(id, nombre, ciudad, capacidad);
    }
}
