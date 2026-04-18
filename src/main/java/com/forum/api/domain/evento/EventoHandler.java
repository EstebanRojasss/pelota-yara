package com.forum.api.domain.evento;

import com.forum.api.domain.model.MatchEvent;
import com.forum.api.domain.model.Partido;

public interface EventoHandler {
    void manejarEvento(Partido partido, MatchEvent evento);

}
