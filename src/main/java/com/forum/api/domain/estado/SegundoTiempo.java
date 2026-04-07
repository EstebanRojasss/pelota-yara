package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;

import java.util.Random;

public class SegundoTiempo implements EstadoPartido {
    private Partido partido;
    private final Random random = new Random();

    public void ejecutar(Partido partido) {
        if (partido.getMinutoActual() == 90) {
            partido.setMinutoAdicional2T(generarMinutoAdicionalRandom());
        }
        if (partido.getMinutoActual() == partido.getMinutoAdicional2T() + partido.getMinutoActual()) {
            partido.cambiarEstado(new FinPartido());
            partido.setStatus(StatusPartido.FINALIZADO);
        }
        partido.aumentarMinuto();
    }

    private int generarMinutoAdicionalRandom() {
        return random.nextInt(0, 11);
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public EstadoPartido getTipoEstadoPartido() {
        return partido.getEstadoPartido();
    }
}

