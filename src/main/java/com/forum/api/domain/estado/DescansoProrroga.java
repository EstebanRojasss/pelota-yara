package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DescansoProrroga implements EstadoPartido {

    private static final Logger log = LoggerFactory.getLogger(DescansoProrroga.class);

    @Override
    public void ejecutar(Partido partido) {

        if(partido.getStatus() == StatusPartido.TIEMPO_EXTRA){
            partido.cambiarEstado( new TiempoExtra() );
        }
        if(partido.getStatus() == StatusPartido.FINALIZADO){
            partido.cambiarEstado( new Finalizado() );
        }
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }

}
