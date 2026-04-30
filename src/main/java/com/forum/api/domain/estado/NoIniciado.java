package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;

public class NoIniciado implements EstadoPartido{

    @Override
    public void ejecutar(Partido partido) {
        if (partido.getStatus().equals(StatusPartido.PRIMER_TIEMPO)) {
            partido.cambiarEstado(new PrimerTiempo());
        }
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }
}
