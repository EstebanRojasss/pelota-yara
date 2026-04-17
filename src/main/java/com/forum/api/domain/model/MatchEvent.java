package com.forum.api.domain.model;

import java.util.Objects;

public class MatchEvent {
    private Long id;
    private Partido partido;
    private Equipo equipo;
    private Jugador jugador;
    private Integer minuto;
    private TipoEventoPartido tipoEventoPartido;

    private MatchEvent(Long id, Partido partido, Equipo equipo, Jugador jugador, Integer minuto, TipoEventoPartido tipoEventoPartido) {
        this.id = id;
        this.partido = partido;
        this.equipo = equipo;
        this.jugador = jugador;
        this.minuto = minuto;
        this.tipoEventoPartido = tipoEventoPartido;
    }

    public static MatchEvent restoreMatchEvent(Long id, Partido partido, Equipo equipo, Jugador jugador, Integer minuto, TipoEventoPartido tipoEventoPartido) {
        return new MatchEvent(id, partido, equipo, jugador, minuto, tipoEventoPartido);
    }

    public static MatchEvent generateMatchEvent(Partido partido, Equipo equipo, Jugador jugador, Integer minuto, TipoEventoPartido tipoEventoPartido) {
        return switch (tipoEventoPartido) {
            case GOL , TARGETA_AMARILLA, TARGETA_ROJA, FALTA, SUSTITUCION ->  new MatchEvent(null, partido, equipo, jugador, minuto, tipoEventoPartido);
            default -> new MatchEvent(null, partido, null, null, null, tipoEventoPartido);
        };
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Partido getPartido() {
        return this.partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Integer getMinuto() {
        return this.minuto;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public TipoEventoPartido getEventoPartido() {
        return this.tipoEventoPartido;
    }

    public void setEventoPartido(TipoEventoPartido tipoEventoPartido) {
        this.tipoEventoPartido = tipoEventoPartido;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        MatchEvent that = (MatchEvent)o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.partido, that.partido) && Objects.equals(this.equipo, that.equipo) && Objects.equals(this.jugador, that.jugador) && Objects.equals(this.minuto, that.minuto) && this.tipoEventoPartido == that.tipoEventoPartido;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.partido, this.equipo, this.jugador, this.minuto, this.tipoEventoPartido);
    }
}

