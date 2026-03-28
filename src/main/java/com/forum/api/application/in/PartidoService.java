package com.forum.api.application.in;

import com.forum.api.domain.Partido;

import java.util.Optional;


public interface PartidoService {

    Partido encontrarPartido(Long id);
    void borrarPartido(Long id);
    Partido guardarNuevoPartido(Partido partido);
    Partido actualizarDatosDePartido(Partido partido);

}
