package com.forum.api.domain;

import jakarta.persistence.Entity;

@Entity
public class MatchEvent {
    private Long id;
    private Partido partido;
    private Equipo equipo;
    private Jugador jugador;
}
