package com.forum.api.domain.service;

import com.forum.api.domain.model.EventoPartido;
import com.forum.api.domain.model.Partido;

public interface GeneradorEventos {
    EventoPartido generarEvento(Partido var1);
}

