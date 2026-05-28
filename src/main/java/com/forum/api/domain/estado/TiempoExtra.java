package com.forum.api.domain.estado;

import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TiempoExtra extends AbstractEstadoPartido {

    private static final Logger log = LoggerFactory.getLogger(TiempoExtra.class);

    @Override
    public void ejecutar(Partido partido) {
        log.info("Tiempo extra: {} vs {} Minuto: {}",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre(), partido.getMinutoActual());
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

        if (minutoActual >= 105 && partido.getStatus() == StatusPartido.DESCANSO_TIEMPO_EXTRA) {
            partido.cambiarEstado(new DescansoProrroga());
        } else if (minutoActual >= 120 && partido.getStatus() == StatusPartido.TANDA_PENALES) {
            partido.cambiarEstado(new TandaPenales());
        }

        return null;
    }

    @Override
    public void onEnter(Partido partido) {
        log.info("Estado en OnEnterPartido: {}", partido.getStatus());
        partido.getStoreEvent().cambiarFase(StatusPartido.TIEMPO_EXTRA);
        log.info("Estado en OnEnterPartido: {}", partido.getStatus());
        partido.inicializarStoreEvent(partido.getStatus());
    }

    @Override
    public void onExit(Partido partido) {
        partido.faseTerminada(true);
    }
}
