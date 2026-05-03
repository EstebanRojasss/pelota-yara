package com.forum.api.domain.model;


public class Liga {
    private Long id;
    private String nombre;
    private String pais;
    private Long fixtureLigaId;
    private Integer temporada;

    private Liga(Long id, String nombre, String pais, Long fixtureLigaId, Integer temporada) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.fixtureLigaId = fixtureLigaId;
        this.temporada = temporada;
    }

    public static Liga create(String nombre, String pais, Long fixtureLigaId, Integer temporada){
        return new Liga(null, nombre, pais, fixtureLigaId, temporada);
    }

    public static Liga restore(Long id, String nombre, String pais, Long fixtureLigaId, Integer temporada){
        return new Liga(id, nombre, pais, fixtureLigaId, temporada);
    }


    public Long getFixtureLigaId() {
        return fixtureLigaId;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getTemporada() {
        return temporada;
    }

}
