package com.forum.api.application.out;

import com.forum.api.domain.model.evento.EventoDelPartido;

import java.util.Optional;

public interface EventoDelPartidoRepository {
     EventoDelPartido saveMatchEvent(EventoDelPartido var1);

     void deleteMatchEvent(Long var1);

     Optional<EventoDelPartido> findMatchEventById(Long var1);
}

