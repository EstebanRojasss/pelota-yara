package com.forum.api.domain.estado;

import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void onEnter(Partido partido) {
        log.info("Estado en OnEnterPartido: {}", partido.getStatus());
        partido.getStoreEvent().cambiarFase(StatusPartido.PRIMER_TIEMPO);
        log.info("Estado en OnEnterPartido: {}", partido.getStatus());
        partido.inicializarStoreEvent(partido.getStatus());
    }


    @Override
    public String nombreEstado() {
        return "Primer Tiempo";
    }

}

