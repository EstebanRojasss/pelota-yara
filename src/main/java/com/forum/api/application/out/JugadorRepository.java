package com.forum.api.application.out;

import com.forum.api.domain.model.Jugador;

import java.util.List;
import java.util.Optional;

public interface JugadorRepository {
     Optional<Jugador> encontrarJugador(Long var1);

     Jugador guardarJugador(Jugador var1);

     void borrarJugador(Long var1);

     List<Jugador> listarJugadoresPorEquipo(Long var1);
}

