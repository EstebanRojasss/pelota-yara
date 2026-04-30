package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Descanso implements EstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(Descanso.class);

    public void ejecutar(Partido partido) {

        if (partido.getStatus().equals(StatusPartido.SEGUNDO_TIEMPO)) {
            log.info("ENTRANDO AL SEGUNDO TIEMPO. \n");
            partido.cambiarEstado(new SegundoTiempo());
        }

    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }

}

