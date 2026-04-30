package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TandaPenales implements EstadoPartido {

    private static final Logger log = LoggerFactory.getLogger(TandaPenales.class);

    @Override
    public void ejecutar(Partido partido) {
        if (!laPelotaSeEstaMoviendo(partido.getStatus())) {
            log.info("PARTIDO FINALIZADO DESPUES DE TANDA DE PENALTIES\n");
            partido.cambiarEstado(new Finalizado());
        }
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }

    public boolean laPelotaSeEstaMoviendo(StatusPartido status) {
        return
                status == StatusPartido.SEGUNDO_TIEMPO ||
                        status == StatusPartido.TIEMPO_EXTRA ||
                        status == StatusPartido.TANDA_PENALES;
    }
}
