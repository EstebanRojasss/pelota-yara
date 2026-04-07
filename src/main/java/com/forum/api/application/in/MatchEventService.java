package com.forum.api.application.in;

import com.forum.api.domain.model.MatchEvent;

import java.util.Set;

public interface MatchEventService {

     MatchEvent agregarNuevoMatchEvent(MatchEvent var1);

     void borrarMatchEvent(Long var1);

     Set<MatchEvent> listarMatchEvents();

     MatchEvent encotrarMatchEvent(Long var1);
}

