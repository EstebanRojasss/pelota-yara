package com.forum.api.domain.evento.gol;

import com.forum.api.domain.evento.EventoHandler;
import com.forum.api.domain.model.EventoDelPartido;
import com.forum.api.domain.model.Partido;

public class GolNormal implements EventoHandler {

    @Override
    public void manejarEvento(Partido partido, EventoDelPartido evento) {
        partido.aumentarMarcador(evento.getEquipo());
    }
}
