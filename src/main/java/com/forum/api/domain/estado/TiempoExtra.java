package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class TiempoExtra implements EstadoPartido {

    private static final Logger log = LoggerFactory.getLogger(TiempoExtra.class);

    @Override
    public void ejecutar(Partido partido) {

        int minutoActual = calcularMinutoActual(partido);
        partido.setMinutoActual(minutoActual);
        log.info("{} vs {} : 1T: {}",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre(), partido.getMinutoActual());

        if (minutoActual >= 105 && partido.getStatus() == StatusPartido.DESCANSO_TIEMPO_EXTRA) {
            partido.cambiarEstado(new DescansoProrroga());
        } else if (minutoActual >= 120 && partido.getStatus() == StatusPartido.TANDA_PENALES) {
            partido.cambiarEstado(new TandaPenales());
        }
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return partido.calcularMinutoActual();
    }

    public boolean laPelotaSeEstaMoviendo(StatusPartido status) {
        return
                status == StatusPartido.SEGUNDO_TIEMPO ||
                        status == StatusPartido.TIEMPO_EXTRA ||
                        status == StatusPartido.TANDA_PENALES ||
                        status == StatusPartido.DESCANSO_TIEMPO_EXTRA;
    }
}
