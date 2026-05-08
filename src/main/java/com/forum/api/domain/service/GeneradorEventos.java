package com.forum.api.domain.service;

import com.forum.api.domain.model.evento.TipoEventoPartido;
import com.forum.api.domain.model.partido.Partido;

public interface GeneradorEventos {
    TipoEventoPartido generarEvento(Partido var1);
}

