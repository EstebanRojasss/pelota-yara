package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinPartido implements EstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(FinPartido.class);
    private Partido partido;

    public void ejecutar(Partido partido) {
        partido.setStatus(StatusPartido.FINALIZADO);
        log.info("Fin del partido {}" ,partido.getStatus().name());
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public EstadoPartido getTipoEstadoPartido() {
        return partido.getEstadoPartido();
    }
}

