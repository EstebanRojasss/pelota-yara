package com.forum.api.engine;


import com.forum.api.application.in.MatchEventService;
import com.forum.api.domain.EventoPartido;
import com.forum.api.domain.MatchEvent;
import com.forum.api.domain.Partido;
import com.forum.api.domain.StatusPartido;

import java.util.Random;

public class MatchTask implements Runnable {
    private final Random random = new Random();

    private final Partido partido;
    private final MatchEventService service;
    private final MatchEngine engine;

    public MatchTask(Partido partido, MatchEventService service, MatchEngine engine) {
        this.partido = partido;
        this.service = service;
        this.engine = engine;
    }

    @Override
    public void run() {

        try {
            while (!partido.getStatus().equals(StatusPartido.FINALIZADO)) {

                Thread.sleep(5000);

                if (partido.getStatus().equals(StatusPartido.MEDIO_TIEMPO)) {
                    continue;
                }

                partido.aumentarMinuto();

                MatchEvent event = engine.siguienteEvento(partido);

                if(event.getEventoPartido().equals(EventoPartido.DESCANSO)){
                    Thread.sleep(10000);
                }

                partido.aplicarEvento(event);
            }
        } catch (InterruptedException e) {

        }

    }
}
