package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class PrimerTiempo implements EstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(PrimerTiempo.class);

    public void ejecutar(Partido partido) {

        int minutoActual = calcularMinutoActual(partido);
        partido.setMinutoActual(minutoActual);
        log.info("{} vs {} : 1T: {}",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre(), partido.getMinutoActual());
        if (minutoActual >= 45 && partido.getStatus().equals(StatusPartido.MEDIO_TIEMPO)) {
            log.info("ENTRANDO AL DESCANSO DE MEDIO TIEMPO:\n MINUTO: {}", partido.getMinutoBase());
            partido.cambiarEstado(new Descanso());
        }
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return partido.calcularMinutoActual();
    }
}

