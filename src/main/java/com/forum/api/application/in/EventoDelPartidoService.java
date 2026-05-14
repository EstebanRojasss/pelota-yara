package com.forum.api.application.in;

import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;

import java.util.List;
import java.util.Set;

public interface EventoDelPartidoService {

     EventoDelPartido agregarNuevoEventoDelPartido(EventoDelPartido eventoDelPartido);

     void borrarEventoDelPartido(Long id);

     List<EventoDelPartido> listarEventosDelPartidoDB();

     EventoDelPartido encontrarEventoDelPartido(Long id);

     List<EventoDelPartido> listarEventosDelPartidoAPI(Partido partido);
}

