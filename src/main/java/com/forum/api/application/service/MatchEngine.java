package com.forum.api.application.service;

import com.forum.api.application.in.JugadorService;
import com.forum.api.application.in.usecases.EngineUseCase;
import com.forum.api.domain.model.*;
import com.forum.api.domain.service.GeneradorEventos;
import com.forum.api.domain.service.GeneradorEventosRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class MatchEngine implements EngineUseCase {
    private static final Logger log = LoggerFactory.getLogger(MatchEngine.class);
    private final GeneradorEventos generadorEventos = new GeneradorEventosRandom();
    private final JugadorService jugadorService;
    private final Random random = new Random();
    private final Map<Equipo, List<Jugador> > jugadoresDeEquipo = new HashMap<>();

    public MatchEngine(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    public MatchEvent generarSiguienteEvento(Partido partido) {
        TipoEventoPartido evento = generadorEventos.generarEvento(partido);
        Equipo equipo = equipoRandom(partido);
        Jugador jugador = jugadorRandom(equipo);
        return MatchEvent.generateMatchEvent(partido, equipo, jugador, partido.getMinutoBase(), evento);
    }

    private Equipo equipoRandom(Partido partido) {
        return partido.equiposDelPartido().get(random.nextInt(0, 2));
    }

    private Jugador jugadorRandom(Equipo equipo) {

        if(!jugadoresDeEquipo.containsKey(equipo)){
            jugadoresDeEquipo.put(
                    equipo,
                    jugadorService.listarJugadoresEquipo(equipo.getId())
            );
            log.info("---JUGADORES DEL EQUIPO---: {} {}",
                    equipo.getId(), jugadoresDeEquipo.get(equipo));
        }

        // primero busca la lista de jugadores del equipo, luego realiza una seleccion random en la lista de jugadores
        return jugadoresDeEquipo.get(equipo)
                .get(random.nextInt(0, jugadoresDeEquipo.get(equipo).size()));
    }

    private int minutoAdicionalRandom() {
        return this.random.nextInt(1, 11);
    }
}

