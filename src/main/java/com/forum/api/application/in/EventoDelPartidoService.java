package com.forum.api.application.in;

import com.forum.api.domain.model.evento.EventoDelPartido;

import java.util.List;
import java.util.Set;

public interface EventoDelPartidoService {

     EventoDelPartido agregarNuevoEventoDelPartido(EventoDelPartido eventoDelPartido);

     void borrarEventoDelPartido(Long id);

     Set<EventoDelPartido> listarEventosDelPartidoDB();

     EventoDelPartido encontrarEventoDelPartido(Long id);

     List<EventoDelPartido> listarEventosDelPartidoAPI();
}

