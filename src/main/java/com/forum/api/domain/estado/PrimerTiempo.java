package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;

import java.util.Random;

public class PrimerTiempo implements EstadoPartido {
    private Partido partido;
    private final Random random = new Random();

    public void ejecutar(Partido partido) {
        if (partido.getMinutoActual() == 45) {
            partido.setMinutoAdicional1T(generarMinutoAdicionalRandom());
        }
        if (partido.getMinutoActual() == partido.getMinutoActual() + partido.getMinutoAdicional1T()) {
            partido.cambiarEstado(new Descanso());
            partido.setStatus(StatusPartido.MEDIO_TIEMPO);
        }
        partido.aumentarMinuto();
    }

    private int generarMinutoAdicionalRandom() {
        return this.random.nextInt(0, 10);
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public EstadoPartido getTipoEstadoPartido() {
        return this.partido.getEstadoPartido();
    }
}

