package com.forum.api.application.in;

import com.forum.api.domain.model.evento.EventoDelPartido;

import java.util.Set;

public interface MatchEventService {

     EventoDelPartido agregarNuevoMatchEvent(EventoDelPartido var1);

     void borrarMatchEvent(Long var1);

     Set<EventoDelPartido> listarMatchEvents();

     EventoDelPartido encotrarMatchEvent(Long var1);
}

