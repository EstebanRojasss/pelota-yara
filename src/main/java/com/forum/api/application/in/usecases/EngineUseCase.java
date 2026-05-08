package com.forum.api.application.in.usecases;

import com.forum.api.domain.model.EventoDelPartido;
import com.forum.api.domain.model.Partido;

public interface EngineUseCase {
     EventoDelPartido generarSiguienteEvento(Partido partido);
}

