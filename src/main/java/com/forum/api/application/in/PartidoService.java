package com.forum.api.application.in;

import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.domain.model.Partido;

import java.util.List;
import java.util.Optional;

public interface PartidoService {
     Partido encontrarPartido(Long var1);

     void borrarPartido(Long var1);

     Partido guardarPartido(CrearPartidoCommand var1);

     Partido actualizarDatosDePartido(Partido var1);

     List<Partido> encontrarTodosLosPartidosEnVivo();

     List<Partido> listarTodosLosPartidos();

    Optional<Partido> encontrarPartidoPorFixtureId(Long id);

    List<Partido> partidosEnVivo();
}

