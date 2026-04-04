package com.forum.api.application.in;

import com.forum.api.domain.model.Partido;
import java.util.List;


public interface PartidoService {

    Partido encontrarPartido(Long id);
    void borrarPartido(Long id);
    Partido guardarNuevoPartido(Partido partido);
    Partido actualizarDatosDePartido(Partido partido);
    List<Partido> encontrarTodosLosPartidosEnVivo();
}
