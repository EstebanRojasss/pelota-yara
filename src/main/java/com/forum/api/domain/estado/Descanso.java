package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Descanso extends AbstractEstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(Descanso.class);

    public void ejecutar(Partido partido) {
        log.info("Descanso {} vs {}: ",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre());
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 45;
    }

    @Override
    public EstadoPartido siguienteEstado(Partido partido) {

        if(!partidoEnJuego(partido.getStatus())){
            return new Finalizado();
        }

        if (partido.getStatus().equals(StatusPartido.SEGUNDO_TIEMPO)) {
            return new SegundoTiempo();
        }

        return null;
    }

    @Override
    public boolean partidoEnJuego(StatusPartido status) {
        return super.partidoEnJuego(status);
    }

    @Override
    public void onEnter(Partido partido) {
        log.info("Entrando al descanso.");
    }

    @Override
    public void onExit(Partido partido) {
        log.info("Iniciando la segunda mitad.");
    }

    @Override
    public String nombreEstado() {
        return "Descanso";
    }

}

