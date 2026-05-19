package com.forum.api.application.service;

import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.partido.Partido;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PartidoContextService {
    private final Map<Long, PartidoRuntimeContext> contextos =
            new ConcurrentHashMap<>();

    public void inicializarContexto(Partido partido, List<Jugador> jugadores) {
        contextos.put(
                partido.getFixtureId(),
                new PartidoRuntimeContext(
                        partido,
                        jugadores
                )
        );
    }

    public PartidoRuntimeContext obtenerContexto(Long fixtureId) {
        return contextos.get(fixtureId);
    }

    public void eliminarContexto(Long fixtureId) {
        contextos.remove(fixtureId);
    }
}
