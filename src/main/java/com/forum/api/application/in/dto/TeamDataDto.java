package com.forum.api.application.in.dto;


public record TeamDataDto(Long id,
                          String nombre,
                          String logo,
                          String pais,
                          Integer fundacion) {

    public static TeamDataDto map(Long id, String nombre, String logo,String pais, Integer fundacion){
        return new TeamDataDto(id, nombre, logo, pais, fundacion);
    }


}
