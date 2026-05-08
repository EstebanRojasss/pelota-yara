package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TandaPenales extends AbstractEstadoPartido {

    private static final Logger log = LoggerFactory.getLogger(TandaPenales.class);

    @Override
    public void ejecutar(Partido partido) {
        log.info("Tanda Penales: {} vs {}: ",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre());
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }

    @Override
    public EstadoPartido siguienteEstado(Partido partido) {
        if(!partidoEnJuego(partido.getStatus())){
            return new Finalizado();
        }

        return null;
    }

    @Override
    public boolean partidoEnJuego(StatusPartido status) {
        return super.partidoEnJuego(status);
    }
}
