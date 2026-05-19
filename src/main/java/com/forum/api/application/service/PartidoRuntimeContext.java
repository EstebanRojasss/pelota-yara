package com.forum.api.application.service;


import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.partido.Partido;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartidoRuntimeContext {

    private final Map<Long, Jugador> jugadoresPorId;
    private final Partido partido;

    public PartidoRuntimeContext(Partido partido, List<Jugador> jugadores) {
        this.jugadoresPorId = jugadores
                .stream()
                .collect(Collectors.toConcurrentMap(
                                Jugador::getFixtureId,
                                j -> j
                        )
                );
        this.partido = partido;
    }


    public Jugador consultarJugador(Long idJugador) {
        return jugadoresPorId.get(idJugador);
    }

    public void consultarYAgregarJugadorSiNoExiste(Jugador jugador) {
        jugadoresPorId.putIfAbsent(jugador.getFixtureId(), jugador);
    }


    public Map<Long, Jugador> getJugadoresPorId() {
        return jugadoresPorId;
    }
}
