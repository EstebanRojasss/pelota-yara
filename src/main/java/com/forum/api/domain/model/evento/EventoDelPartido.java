package com.forum.api.domain.model.evento;

import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.partido.Partido;

import java.util.Objects;

public class EventoDelPartido {
    private final Long id;
    private Equipo equipo;
    private Jugador jugador;
    private Integer minuto;
    private TipoEvento tipo;

    private EventoDelPartido(Long id,Equipo equipo, Jugador jugador, Integer minuto, TipoEvento tipo) {
        this.id = id;
        this.equipo = equipo;
        this.jugador = jugador;
        this.minuto = minuto;
        this.tipo = tipo;
    }

    public static EventoDelPartido crearEventoDelPartido(Equipo equipo, Jugador jugador, Integer minuto, TipoEvento tipo){
        return new EventoDelPartido(
                null,
                equipo,
                jugador,
                minuto,
                tipo
        );
    }

    public static EventoDelPartido restaurarEventoDelPartido(Long id, Equipo equipo, Jugador jugador, Integer minuto, TipoEvento tipo) {
        return new EventoDelPartido(id, equipo, jugador, minuto, tipo);
    }

    public static EventoDelPartido generateMatchEvent(Partido partido, Equipo equipo, Jugador jugador, Integer minuto, BORRARDESPUES BORRARDESPUES) {
//        return switch (BORRARDESPUES) {
//            case GOL , TARGETA_AMARILLA, TARGETA_ROJA, FALTA, SUSTITUCION ->  new EventoDelPartido(null, partido, equipo, jugador, minuto);
//            default -> new EventoDelPartido(null, partido, null, null, null);
//        };
        return null;
    }

    public Long getId() {
        return this.id;
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

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        EventoDelPartido that = (EventoDelPartido)o;
        return Objects.equals(this.id, that.id) && Objects.equals(this.equipo, that.equipo) && Objects.equals(this.jugador, that.jugador) && Objects.equals(this.minuto, that.minuto);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.equipo, this.jugador, this.minuto);
    }
}

