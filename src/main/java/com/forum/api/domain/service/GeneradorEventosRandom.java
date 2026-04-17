package com.forum.api.domain.service;

import com.forum.api.domain.model.TipoEventoPartido;
import com.forum.api.domain.model.Partido;

import java.util.Random;

public class GeneradorEventosRandom implements GeneradorEventos {
    private static Integer sumaProbabilidades = 0;
    private static final Random random = new Random();

    public TipoEventoPartido generarEvento(Partido partido) {
        int dardo = random.nextInt(sumaProbabilidades);
        for (TipoEventoPartido evento : TipoEventoPartido.values()) {
            if (dardo < evento.getProbabilidad()) {
                return evento;
            }
            dardo -= evento.getProbabilidad();
        }
        return TipoEventoPartido.values()[TipoEventoPartido.values().length - 1];
    }

    static {
        for (TipoEventoPartido e : TipoEventoPartido.values()) {
            sumaProbabilidades = sumaProbabilidades + e.getProbabilidad();
        }
    }
}

