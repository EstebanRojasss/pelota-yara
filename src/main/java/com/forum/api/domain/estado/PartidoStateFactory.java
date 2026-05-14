package com.forum.api.domain.estado;

import com.forum.api.domain.model.partido.StatusPartido;

public class PartidoStateFactory {

    public static EstadoPartido sincronizarEstado(StatusPartido status){
        return switch (status){
            case NO_INICIADO -> new NoIniciado();
            case PRIMER_TIEMPO -> new PrimerTiempo();
            case MEDIO_TIEMPO -> new Descanso();
            case SEGUNDO_TIEMPO -> new SegundoTiempo();
            case TIEMPO_EXTRA -> new TiempoExtra();
            case DESCANSO_TIEMPO_EXTRA -> new DescansoProrroga();
            case TANDA_PENALES -> new TandaPenales();
            case FINALIZADO -> new Finalizado();
        };
    }
}
