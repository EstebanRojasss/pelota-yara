package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class SegundoTiempo extends AbstractEstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(SegundoTiempo.class);

    public void ejecutar(Partido partido) {
        log.info("Segundo Tiempo {} vs {} - Minuto: {}",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre(),
                partido.getMinutoActual());

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

        if (minutoActual >= 90 && partido.getStatus() == StatusPartido.TIEMPO_EXTRA) {
            partido.cambiarEstado(new TiempoExtra());
        }

        return null;
    }

    @Override
    public void onEnter(Partido partido) {
        partido.setMinutoBase(45);
    }


    @Override
    public String nombreEstado() {
        return "Segundo Tiempo";
    }
}

