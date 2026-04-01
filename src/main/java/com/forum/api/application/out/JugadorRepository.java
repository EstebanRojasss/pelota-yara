package com.forum.api.application.out;

import com.forum.api.domain.model.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorRepository {

    Optional<Jugador> encontrarJugador(Long id);

    Jugador guardarJugador(Jugador jugador);

    void borrarJugador(Long id);

    List<Jugador> listarJugadoresPorEquipo(Long id);
}
