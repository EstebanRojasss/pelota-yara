package com.forum.api.infra.adapter.in.scheduler;

import com.forum.api.application.in.MatchEventService;
import com.forum.api.application.in.PartidoService;
import com.forum.api.application.in.usecases.EngineUseCase;
import com.forum.api.domain.model.MatchEvent;
import com.forum.api.domain.model.Partido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MatchTaskSchedule {
    private static final Logger log = LoggerFactory.getLogger(MatchTaskSchedule.class);
    private final MatchEventService matchService;
    private final EngineUseCase engine;
    private final PartidoService partidoService;

    public MatchTaskSchedule(MatchEventService matchService, EngineUseCase engine, PartidoService partidoService) {
        this.matchService = matchService;
        this.engine = engine;
        this.partidoService = partidoService;
    }

    private Partido partidoEnProceso() {
        return partidoService.encontrarTodosLosPartidosEnVivo().getFirst();
    }

    @Scheduled(fixedRate=7000)
    public void ejecutarPartido() {
        //Encontrar partidos en vivo
        Partido partido = partidoEnProceso();

        log.info("MINUTO PARTIDO: {}", partido.getMinutoActual());

        //ejecutar estados
        partido.ejecutar();

        MatchEvent evento = engine.generarSiguienteEvento(partido);

        partido.aplicarEvento(evento);

        partidoService.actualizarDatosDePartido(partido);

        matchService.agregarNuevoMatchEvent(evento);
    }
}

