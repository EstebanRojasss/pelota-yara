package com.forum.api.infra.adapter.in.scheduler;

import com.forum.api.application.in.PartidoService;
import com.forum.api.application.in.SSeBroadcastUseCase;
import com.forum.api.domain.model.Partido;
import com.forum.api.infra.adapter.in.rest.dto.PartidoResponseDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApiCallScheduler {

    private final PartidoService partidoService;
    private final SSeBroadcastUseCase broadcastUseCase;

    public ApiCallScheduler(PartidoService partidoService, SSeBroadcastUseCase broadcastUseCase) {
        this.partidoService = partidoService;
        this.broadcastUseCase = broadcastUseCase;
    }

    @Scheduled(fixedRate = 60000)
    public void llamarApiFootballVivo(){
       List<Partido> partidos = partidoService.encontrarTodosLosPartidosEnVivo();
       partidos.forEach(Partido::ejecutar);

       List<PartidoResponseDto> partidosDto = partidos
               .stream()
               .map( p ->{
                   p.actualizarMinutoActual();
                   return PartidoResponseDto.fromDomainExistent(p);
               })
               .toList();

       broadcastUseCase.broadcast(partidosDto);
    }
}
