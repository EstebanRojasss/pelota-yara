package com.forum.api.domain.model;

import java.util.Objects;

public class MatchEvent {
    private Long id;
    private Set<Partido> partidos;
    private Set<Equipo> equipos;
    private Jugador jugador;
    private Integer minuto;
    private EventoPartido eventoPartido;

    private MatchEvent(Long id, Set<Partido> partidos, Set<Equipo> equipos, Jugador jugador, Integer minuto, EventoPartido eventoPartido) {
        this.id = id;
        this.partidos = partidos;
        this.equipos = equipos;
        this.jugador = jugador;
        this.minuto = minuto;
        this.eventoPartido = eventoPartido;
    }

    public static MatchEvent restoreMatchEvent(Long id, Set<Partido> partidos, Set<Equipo> equipos , Jugador jugador, Integer minuto, EventoPartido eventoPartido){
        return new MatchEvent(
                id,
                partidos,
                equipos,
                jugador,
                minuto,
                eventoPartido
        );
    }

    public static MatchEvent generateMatchEvent(Partido partido, Equipo equipo, Jugador jugador, Integer minuto, EventoPartido eventoPartido){
        return switch (eventoPartido) {
            case GOL, FALTA, TARGETA_ROJA, TARGETA_AMARILLA ->
                    new MatchEvent(null, partido, equipo, jugador, minuto, eventoPartido);
            default -> new MatchEvent(null, partido, null, null, null, eventoPartido);
        };
    }

    private void addPartido(Partido partido){
        this.partidos.add(partido);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public EventoPartido getEventoPartido() {
        return eventoPartido;
    }

    public void setEventoPartido(EventoPartido eventoPartido) {
        this.eventoPartido = eventoPartido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatchEvent that = (MatchEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(partido, that.partido) && Objects.equals(equipo, that.equipo) && Objects.equals(jugador, that.jugador) && Objects.equals(minuto, that.minuto) && eventoPartido == that.eventoPartido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partido, equipo, jugador, minuto, eventoPartido);
    }
}


