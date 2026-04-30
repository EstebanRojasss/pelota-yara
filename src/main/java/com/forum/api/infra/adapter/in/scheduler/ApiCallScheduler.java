package com.forum.api.infra.adapter.in.scheduler;

import com.forum.api.application.in.PartidoService;
import com.forum.api.domain.model.Partido;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApiCallScheduler {

    private final PartidoService partidoService;

    public ApiCallScheduler(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @Scheduled(fixedRate = 60000)
    public void llamarApiFootballVivo(){
        partidoService.encontrarTodosLosPartidosEnVivo().
                forEach(Partido::ejecutar);
    }
}
