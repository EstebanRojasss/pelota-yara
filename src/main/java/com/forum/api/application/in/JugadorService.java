package com.forum.api.application.in;

import com.forum.api.domain.model.Jugador;


import java.util.List;

public interface JugadorService {
    Jugador agregarNuevoJugador(Jugador jugador);
    List<Jugador> listarJugadoresEquipo(Long equipoId);
    Jugador encontrarJugadorPorId(Long id);
    void eliminarJugadorPorId(Long id);
    Jugador actualizarDatosJugador(Jugador jugador);
}
