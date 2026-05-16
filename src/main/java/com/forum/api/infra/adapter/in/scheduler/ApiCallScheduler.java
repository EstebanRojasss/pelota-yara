package com.forum.api.infra.adapter.in.scheduler;

import com.forum.api.application.in.EventoDelPartidoService;
import com.forum.api.application.in.PartidoService;
import com.forum.api.application.in.SSeBroadcastUseCase;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;
import com.forum.api.infra.adapter.in.rest.dto.PartidoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ApiCallScheduler {

    private final PartidoService partidoService;
    private final EventoDelPartidoService eventoService;
    private final SSeBroadcastUseCase broadcastUseCase;

    public ApiCallScheduler(PartidoService partidoService, EventoDelPartidoService eventoService, SSeBroadcastUseCase broadcastUseCase) {
        this.partidoService = partidoService;
        this.eventoService = eventoService;
        this.broadcastUseCase = broadcastUseCase;
    }

    @Scheduled(fixedRate = 60000)
    public void llamarApiFootballVivo() {
        List<Partido> partidos = partidoService.encontrarTodosLosPartidosEnVivo();
        partidos.forEach(Partido::ejecutar);

        List<PartidoResponseDto> partidosDto = partidos
                .stream()
                .map(p -> {
                    p.actualizarMinutoActual();
                    return PartidoResponseDto.fromDomainExistent(p);
                })
                .toList();

        broadcastUseCase.broadcast(partidosDto);
    }


    @Scheduled(initialDelay = 10000, fixedRate = 120000)
    public void llamarApiFootballEventos() {
        List<Partido> partidos = partidoService.partidosEnVivo();

        Map<Long, List<EventoDelPartido>> eventosPorPartido = new HashMap<>();

        for (Partido partido : partidos) {
            try {
                List<EventoDelPartido> eventos = eventoService.obtenerEventosDelProvider(partido);
                partido.agregarEvento(eventos);
                eventosPorPartido.put(partido.getId(), eventos);

                if (partido.isFaseTerminada()) {
                    eventoService.agregarEventosPorFase(
                            partido.getStoreEvent().obtenerEventosPorFase(partido.getStatus())
                    );
                }
            } catch (Exception e) {
                log.error("Error procesando eventos del partido {}", partido.getId(), e);
            }

        }

        broadcastUseCase.broadcast(eventosPorPartido);
    }
}
