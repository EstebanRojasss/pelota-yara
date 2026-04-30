//package com.forum.api.infra.adapter.in.scheduler;
//
//import com.forum.api.application.in.MatchEventService;
//import com.forum.api.application.in.PartidoService;
//import com.forum.api.application.in.usecases.EngineUseCase;
//import com.forum.api.domain.model.MatchEvent;
//import com.forum.api.domain.model.Partido;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Random;
//import java.util.Set;
//
//@Component
//public class MatchTaskSchedule {
//    private static final Logger log = LoggerFactory.getLogger(MatchTaskSchedule.class);
//    private final MatchEventService matchService;
//    private final EngineUseCase engine;
//    private final PartidoService partidoService;
//    private Set<Partido> partidos = new HashSet<>();
//    private final Random random = new Random();
//
//    public MatchTaskSchedule(MatchEventService matchService, EngineUseCase engine, PartidoService partidoService) {
//        this.matchService = matchService;
//        this.engine = engine;
//        this.partidoService = partidoService;
//    }
//
//    private Set<Partido> partidosEnProceso() {
//        int count = 0;
//        log.info("CONTADOR DE VECES QUE ENTRA EN METODO partidoEnProceso: {} ", count++);
//        return new HashSet<>(partidoService.encontrarTodosLosPartidosEnVivo());
//    }
//
//    @Scheduled(fixedRate = 5000)
//    public void ejecutarPartido() {
//        if (this.partidos.isEmpty()) {
//            partidos = partidosEnProceso();
//        }
//
//        for (Partido partido : partidos) {
//            partido.ejecutar();
//            log.info("PARTIDO: {}", partido.getId());
//            if (random.nextDouble() < 0.6) {
//                MatchEvent evento = engine.generarSiguienteEvento(partido);
//                partido.aplicarEvento(evento);
//                matchService.agregarNuevoMatchEvent(evento);
//            }
//
//            partidoService.actualizarDatosDePartido(partido);
//            log.info("MINUTO PARTIDO: {}", partido.getMinutoBase());
//        }
//
//
//
//    }
//}

