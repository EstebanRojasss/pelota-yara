package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class PrimerTiempo implements EstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(PrimerTiempo.class);
    private final Random random = new Random();
    public void ejecutar(Partido partido) {

        partido.aumentarMinuto();

        if (partido.getMinutoActual() == 45) {
            partido.setMinutoAdicional1T(generarMinutoAdicionalRandom());
            log.info("-----------------MINUTOS ADICIONADOS: {}", partido.getMinutoAdicional1T());
        }
        if (partido.getMinutoActual() == 45 + partido.getMinutoAdicional1T()) {
            log.info("------------ENTRANDO AL DESCANSO--------------------");
            partido.cambiarEstado(new Descanso());
            partido.setStatus(StatusPartido.EN_JUEGO);
        }
    }

    private int generarMinutoAdicionalRandom() {
        return this.random.nextInt(0, 10);
    }

}

