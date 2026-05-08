package com.forum.api.domain.service;

import com.forum.api.domain.model.evento.BORRARDESPUES;
import com.forum.api.domain.model.partido.Partido;

public interface GeneradorEventos {
    BORRARDESPUES generarEvento(Partido var1);
}

