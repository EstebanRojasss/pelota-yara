package com.forum.api.domain.service;

import com.forum.api.domain.model.TipoEventoPartido;
import com.forum.api.domain.model.Partido;

public interface GeneradorEventos {
    TipoEventoPartido generarEvento(Partido var1);
}

