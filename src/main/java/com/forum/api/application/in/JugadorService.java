package com.forum.api.application.in;

import com.forum.api.application.in.dto.evento.PlayerEventDataDto;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorService {
     Jugador agregarNuevoJugador(Jugador jugador);

     List<Jugador> listarJugadoresPorEquipo(Long equipoId);

     Jugador encontrarJugadorPorId(Long jugadorId);

     Optional<Jugador> encontrarJugadorPorFixtureId(Long fixtureId);

     void eliminarJugadorPorId(Long idJugador);

     Jugador retornarOGuardarSiNoExiste(PlayerEventDataDto jugador, Equipo equipo);

     List<Jugador> listarJugadoresDesdeApi(Long id);

}
