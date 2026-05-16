package com.forum.api.application.in;

import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;

import java.util.List;

public interface EventoDelPartidoService {

     EventoDelPartido agregarEvento(EventoDelPartido eventoDelPartido);

     void borrarEventoDelPartido(Long id);

     List<EventoDelPartido> listarEventosDelPartidoDB();

     EventoDelPartido encontrarEventoDelPartido(Long id);

     List<EventoDelPartido> listarEventosDelPartidoAPI(Partido partido);

     List<EventoDelPartido> obtenerEventosDelProvider(Partido partido);

     void agregarEventosPorFase(List<EventoDelPartido> eventoDelPartido);

}

