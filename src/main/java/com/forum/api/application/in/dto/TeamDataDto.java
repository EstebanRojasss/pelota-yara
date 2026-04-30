package com.forum.api.application.in.dto;


public record TeamDataDto(Long id,
                          String nombre,
                          String logo) {

    public static TeamDataDto map(Long id, String nombre, String logo){
        return new TeamDataDto(id, nombre, logo);
    }


}
