package com.forum.api.domain.estado;

import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DescansoProrroga extends AbstractEstadoPartido {

    private static final Logger log = LoggerFactory.getLogger(DescansoProrroga.class);

    @Override
    public void ejecutar(Partido partido) {
        log.info("Descanso tiempo extra: {} vs {}: ",
                partido.getEquipoLocal().getNombre(),
                partido.getEquipoVisitante().getNombre());
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }

    @Override
    public EstadoPartido siguienteEstado(Partido partido) {
        if(partido.getStatus() == StatusPartido.TIEMPO_EXTRA){
            return new TiempoExtra();
        }

        if(partidoEnJuego(partido.getStatus())){
            return new Finalizado();
        }

        return null;
    }

    @Override
    public boolean partidoEnJuego(StatusPartido status) {
        return super.partidoEnJuego(status);
    }
}
