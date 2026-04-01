package com.forum.api.application.in;

import com.forum.api.domain.model.MatchEvent;

import java.util.Set;

public interface MatchEventService {
    MatchEvent agregarNuevoMatchEvent(MatchEvent matchEvent);
    void borrarMatchEvent(Long id);
    Set<MatchEvent> listarMatchEvents();
    MatchEvent encotrarMatchEvent(Long id);
}
