package com.forum.api.application.in;

import com.forum.api.domain.model.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
     Jugador agregarNuevoJugador(Jugador jugador);

     List<Jugador> listarJugadoresEquipoDB(Long idJugador);

     Jugador encontrarJugadorPorId(Long jugadorId);

     Optional<Jugador> encontrarJugadorPorFixtureId(Long fixtureId);

     void eliminarJugadorPorId(Long idJugador);

}
