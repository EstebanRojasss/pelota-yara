package com.forum.api.domain.estado;


import com.forum.api.domain.model.partido.StatusPartido;

public  abstract class AbstractEstadoPartido implements EstadoPartido {


    public boolean partidoEnJuego(StatusPartido status) {
        return status.equals(StatusPartido.PRIMER_TIEMPO) ||
                status.equals(StatusPartido.SEGUNDO_TIEMPO) ||
                status.equals(StatusPartido.MEDIO_TIEMPO) ||
                status.equals(StatusPartido.TIEMPO_EXTRA);
    }

    @Override
    public String nombreEstado() {
        return "";
    }


}
