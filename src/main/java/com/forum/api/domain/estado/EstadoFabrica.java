package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EstadoFabrica {


    private static final Logger log = LoggerFactory.getLogger(EstadoFabrica.class);

    private void transicionarSiEsNecesario(Partido partido){
        EstadoPartido estadoActual = partido.getEstadoPartido();
        EstadoPartido siguienteEstado = estadoActual.siguienteEstado(partido);

        if(siguienteEstado != null && !estadoActual.getClass().equals(siguienteEstado.getClass())){
            log.info("Transicion de {} a {} ", estadoActual.nombreEstado(), siguienteEstado.nombreEstado());

            estadoActual.onExit(partido);
            partido.cambiarEstado(siguienteEstado);
            siguienteEstado.onEnter(partido);

        }
    }

    public void ejecutarEstado(Partido partido){
        EstadoPartido estadoActual = partido.getEstadoPartido();
        estadoActual.ejecutar(partido);
        transicionarSiEsNecesario(partido);
    }
}
