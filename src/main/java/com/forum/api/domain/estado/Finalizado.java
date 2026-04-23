package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Finalizado implements EstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(Finalizado.class);

    public void ejecutar(Partido partido) {
        log.info("Fin del partido:\n Datos del partido: {}" ,partido.toString());
    }
}

