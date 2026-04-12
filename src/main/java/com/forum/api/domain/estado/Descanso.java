package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Descanso implements EstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(Descanso.class);

    public void ejecutar(Partido partido) {
        log.info("ENTRANDO AL SEGUNDO TIEMPO...----------------------------------");
        partido.cambiarEstado(new SegundoTiempo());
        partido.setStatus(StatusPartido.EN_JUEGO);
    }

}

