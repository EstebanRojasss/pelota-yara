package com.forum.api.application.service;

import com.forum.api.application.in.JugadorService;
import com.forum.api.application.in.usecases.EngineUseCase;
import com.forum.api.domain.exception.JugadorNotFoundException;
import com.forum.api.domain.model.*;
import com.forum.api.domain.service.GeneradorEventos;
import com.forum.api.domain.service.GeneradorEventosRandom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MatchEngine implements EngineUseCase {
    private final GeneradorEventos generadorEventos = new GeneradorEventosRandom();
    private final JugadorService jugadorService;
    private final Random random = new Random();
    private List<Jugador> jugadores;

    public MatchEngine(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    public MatchEvent generarSiguienteEvento(Partido partido) {
        EventoPartido evento = generadorEventos.generarEvento(partido);
        Equipo equipo = equipoRandom(partido);
        Jugador jugador = jugadorRandom(equipo);
        return MatchEvent.generateMatchEvent(partido, equipo, jugador, partido.getMinutoActual(), evento);
    }

    private Equipo equipoRandom(Partido partido) {
        return partido.equiposDelPartido().get(random.nextInt(0, 2));
    }

    private Jugador jugadoresDelEquipo(Equipo equipo) {
        if(jugadores.isEmpty()){
             jugadores = jugadorService
                    .listarJugadoresEquipo(equipo.getId());
        }
        throw new JugadorNotFoundException("Ocurrio un error al listar los jugadores");
    }

    private int minutoAdicionalRandom() {
        return this.random.nextInt(1, 11);
    }
}

