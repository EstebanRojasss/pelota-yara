package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoIniciado extends AbstractEstadoPartido{

    private static final Logger log = LoggerFactory.getLogger(NoIniciado.class);

    @Override
    public void ejecutar(Partido partido) {
        log.info("Estado actual: {}", partido.getStatus().name());
        log.info("PARTIDO: {} vs {}",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre());
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }

    @Override
    public EstadoPartido siguienteEstado(Partido partido) {
        if(partido.getStatus().equals(StatusPartido.PRIMER_TIEMPO)){
            return new PrimerTiempo();
        }

        return null;
    }

    @Override
    public void onExit(Partido partido) {
        log.info("Transicionando al primer tiempo. ");
    }
}
