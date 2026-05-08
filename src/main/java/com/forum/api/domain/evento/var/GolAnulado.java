package com.forum.api.domain.evento.var;

import com.forum.api.domain.evento.EventoHandler;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;

public class GolAnulado implements EventoHandler {

    @Override
    public void manejarEvento(Partido partido, EventoDelPartido evento) {
        Equipo equipoEvento = evento.getEquipo();

        partido.restarMarcador(
                equipoEvento.
                        equals(partido.getEquipoLocal()) ? partido.getEquipoLocal() : partido.getEquipoVisitante()
        );
    }
}
