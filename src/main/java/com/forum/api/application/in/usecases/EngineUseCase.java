package com.forum.api.application.in.usecases;

import com.forum.api.domain.model.MatchEvent;
import com.forum.api.domain.model.Partido;

public interface EngineUseCase {
    public MatchEvent generarSiguienteEvento(Partido var1);
}

