package com.forum.api.application.out;

import com.forum.api.domain.model.evento.EventoDelPartido;

import java.util.List;
import java.util.Optional;

public interface EventoDelPartidoRepository {
     EventoDelPartido saveEventoDelPartido(EventoDelPartido eventoDelPartido);

     void deleteEventoDelPartido(Long var1);

     Optional<EventoDelPartido> findEventoDelPartidoById(Long var1);

     List<EventoDelPartido> findAllEventos();

     void saveEventosPorFase(List<EventoDelPartido> eventoDelPartido);
}

