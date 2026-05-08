package com.forum.api.domain.service;

import com.forum.api.domain.model.evento.BORRARDESPUES;
import com.forum.api.domain.model.partido.Partido;

import java.util.Random;

public class GeneradorEventosRandom implements GeneradorEventos {
    private static Integer sumaProbabilidades = 0;
    private static final Random random = new Random();

    public BORRARDESPUES generarEvento(Partido partido) {
        int dardo = random.nextInt(sumaProbabilidades);
        for (BORRARDESPUES evento : BORRARDESPUES.values()) {
            if (dardo < evento.getProbabilidad()) {
                return evento;
            }
            dardo -= evento.getProbabilidad();
        }
        return BORRARDESPUES.values()[BORRARDESPUES.values().length - 1];
    }

    static {
        for (BORRARDESPUES e : BORRARDESPUES.values()) {
            sumaProbabilidades = sumaProbabilidades + e.getProbabilidad();
        }
    }
}

