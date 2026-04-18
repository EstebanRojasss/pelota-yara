package com.forum.api.domain.evento.gol;

import com.forum.api.domain.evento.EventoHandler;
import com.forum.api.domain.model.MatchEvent;
import com.forum.api.domain.model.Partido;

public class GolNormal implements EventoHandler {

    @Override
    public void manejarEvento(Partido partido, MatchEvent evento) {
        partido.aumentarMarcador(evento.getEquipo());
    }
}
