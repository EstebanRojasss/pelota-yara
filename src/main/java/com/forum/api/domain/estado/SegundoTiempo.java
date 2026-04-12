package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SegundoTiempo implements EstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(SegundoTiempo.class);
    private final Random random = new Random();

    public void ejecutar(Partido partido) {

        partido.aumentarMinuto();

        if (partido.getMinutoActual() == 90) {
            partido.setMinutoAdicional2T(generarMinutoAdicionalRandom());
            log.info("MINUTO ADICIONAL 2T: {}", partido.getMinutoAdicional2T());
        }
        if (partido.getMinutoActual() == 90 + partido.getMinutoAdicional2T()) {
            partido.cambiarEstado(new FinPartido());
            partido.setStatus(StatusPartido.FINALIZADO);
        }
    }

    private int generarMinutoAdicionalRandom() {
        return random.nextInt(0, 11);
    }
}

