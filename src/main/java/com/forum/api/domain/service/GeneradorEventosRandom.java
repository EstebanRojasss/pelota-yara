package com.forum.api.domain.service;

import com.forum.api.domain.model.EventoPartido;
import com.forum.api.domain.model.Partido;

import java.util.Random;

public class GeneradorEventosRandom
implements GeneradorEventos {
    private static Integer sumaProbabilidades = 0;
    private static final Random random = new Random();

    public EventoPartido generarEvento(Partido partido) {
        int dardo = random.nextInt(sumaProbabilidades);
        for (EventoPartido evento : EventoPartido.values()) {
            if (dardo < evento.getProbabilidad()) {
                return evento;
            }
            dardo -= evento.getProbabilidad();
        }
        return EventoPartido.values()[EventoPartido.values().length - 1];
    }

    static {
        for (EventoPartido e : EventoPartido.values()) {
            sumaProbabilidades = sumaProbabilidades + e.getProbabilidad();
        }
    }
}

