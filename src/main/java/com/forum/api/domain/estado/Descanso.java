package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;

public class Descanso implements EstadoPartido {
    private Partido partido;

    public void ejecutar(Partido partido) {
        partido.setStatus(StatusPartido.EN_JUEGO);
        partido.cambiarEstado(new SegundoTiempo());
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public EstadoPartido getTipoEstadoPartido() {
        return this.partido.getEstadoPartido();
    }
}

