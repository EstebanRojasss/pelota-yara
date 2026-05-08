package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class TiempoExtra extends AbstractEstadoPartido {

    private static final Logger log = LoggerFactory.getLogger(TiempoExtra.class);

    @Override
    public void ejecutar(Partido partido) {
        log.info("Tiempo extra: {} vs {} Minuto: {}",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre(), partido.getMinutoActual());
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return partido.calcularMinutoActual();
    }

    @Override
    public EstadoPartido siguienteEstado(Partido partido) {
        int minutoActual = calcularMinutoActual(partido);

        if(!partidoEnJuego(partido.getStatus())){
            return new Finalizado();
        }

        if (minutoActual >= 105 && partido.getStatus() == StatusPartido.DESCANSO_TIEMPO_EXTRA) {
            partido.cambiarEstado(new DescansoProrroga());
        } else if (minutoActual >= 120 && partido.getStatus() == StatusPartido.TANDA_PENALES) {
            partido.cambiarEstado(new TandaPenales());
        }

        return null;
    }

}
