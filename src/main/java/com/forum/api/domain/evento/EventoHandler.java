package com.forum.api.domain.evento;

import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;

public interface EventoHandler {
    void manejarEvento(Partido partido, EventoDelPartido evento);
}
