package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class PrimerTiempo extends AbstractEstadoPartido  {
    private static final Logger log = LoggerFactory.getLogger(PrimerTiempo.class);

    public void ejecutar(Partido partido) {
        log.info("Primer tiempo: {} vs {} - Minuto: {}",
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

        if(minutoActual >= 45 && partido.getStatus().equals(StatusPartido.MEDIO_TIEMPO)){
            return new Descanso();
        }

        return null;
    }



    @Override
    public String nombreEstado() {
        return "Primer Tiempo";
    }

}

