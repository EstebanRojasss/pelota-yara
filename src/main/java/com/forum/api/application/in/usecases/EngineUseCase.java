package com.forum.api.application.in.usecases;

import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;

public interface EngineUseCase {
     EventoDelPartido generarSiguienteEvento(Partido partido);
}

