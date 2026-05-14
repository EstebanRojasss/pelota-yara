package com.forum.api.application.in;

import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;

import java.util.List;
import java.util.Optional;

public interface PartidoService {
     Partido encontrarPartido(Long id);

     void borrarPartido(Long id);

     Partido guardarPartido(CrearPartidoCommand id);

     Partido actualizarDatosDePartido(Partido id);

     List<Partido> encontrarTodosLosPartidosEnVivo();

     List<Partido> listarTodosLosPartidos();

    Optional<Partido> encontrarPartidoPorFixtureId(Long id);

    List<Partido> partidosEnVivo();

    List<EventoDelPartido> obtenerNuevosEventos(Partido partido);
}

