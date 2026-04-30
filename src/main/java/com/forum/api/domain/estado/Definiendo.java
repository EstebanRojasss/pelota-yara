package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;

public class Definiendo implements EstadoPartido {

    @Override
    public void ejecutar(Partido partido) {
        switch (partido.getStatus()){
            case NO_INICIADO -> partido.cambiarEstado( new NoIniciado() );
            case PRIMER_TIEMPO -> partido.cambiarEstado( new PrimerTiempo() );
            case MEDIO_TIEMPO -> partido.cambiarEstado( new Descanso() );
            case SEGUNDO_TIEMPO -> partido.cambiarEstado( new SegundoTiempo());
            case TIEMPO_EXTRA -> partido.cambiarEstado( new TiempoExtra());
            case FINALIZADO -> partido.cambiarEstado( new Finalizado() );
            case TANDA_PENALES -> partido.cambiarEstado( new TandaPenales() );
        }
    }

    @Override
    public Integer calcularMinutoActual(Partido partido) {
        return 0;
    }
}
