package com.forum.api.application.in.command;

import com.forum.api.domain.model.Jugador;

public record CrearJugadorCommand(Long id, String name) {

    public static Jugador from(Long fixtureId, String name){
        return Jugador.create(name, null, fixtureId, null);
    }
}
