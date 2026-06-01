package com.forum.api.domain.estado;

import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Finalizado extends AbstractEstadoPartido {
    private static final Logger log = LoggerFactory.getLogger(Finalizado.class);

    @Override
    public Optional<StatusPartido> obtenerFaseFinalizada(Partido partido) {
         return Optional.of(StatusPartido.SEGUNDO_TIEMPO);
    }

    public void ejecutar(Partido partido) {

    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }

    @Override
    public EstadoPartido siguienteEstado(Partido partido) {
        partido.setStatus(StatusPartido.FINALIZADO);
        return null;
    }

    @Override
    public void onEnter(Partido partido) {
        log.info("Fin del partido:\n Datos del partido: {}", partido.toString());
    }

    @Override
    public String nombreEstado() {
        return "Finalizado";
    }
}

