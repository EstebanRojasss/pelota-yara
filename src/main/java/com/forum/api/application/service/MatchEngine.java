package com.forum.api.application.service;

import com.forum.api.application.in.JugadorService;
import com.forum.api.application.in.usecases.EngineUseCase;
import com.forum.api.domain.model.*;
import com.forum.api.domain.service.GeneradorEventos;
import com.forum.api.domain.service.GeneradorEventosRandom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MatchEngine
implements EngineUseCase {
    private final GeneradorEventos generadorEventos = new GeneradorEventosRandom();
    private final JugadorService jugadorService;
    private final Random random = new Random();

    public MatchEngine(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    public MatchEvent generarSiguienteEvento(Partido partido) {
        EventoPartido evento = this.generadorEventos.generarEvento(partido);
        Equipo equipo = this.equipoRandom(partido);
        Jugador jugador = this.jugadorRandom(this.jugadorService.listarJugadoresEquipo(equipo.getId()));
        return MatchEvent.generateMatchEvent(partido, equipo, jugador, partido.getMinutoActual(),evento);
    }

    private Equipo equipoRandom(Partido partido) {
        return partido.equiposDelPartido().get(this.random.nextInt(0, 2));
    }

    private Jugador jugadorRandom(List<Jugador> jugadores) {
        return jugadores.get(this.random.nextInt(0, jugadores.size()));
    }

    private int minutoAdicionalRandom() {
        return this.random.nextInt(1, 11);
    }
}

