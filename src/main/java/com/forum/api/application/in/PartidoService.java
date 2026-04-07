package com.forum.api.application.in;

import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.domain.model.Partido;

import java.util.List;

public interface PartidoService {
    public Partido encontrarPartido(Long var1);

    public void borrarPartido(Long var1);

    public Partido guardarNuevoPartido(CrearPartidoCommand var1);

    public Partido actualizarDatosDePartido(Partido var1);

    public List<Partido> encontrarTodosLosPartidosEnVivo();

    public List<Partido> listarTodosLosPartidos();
}

