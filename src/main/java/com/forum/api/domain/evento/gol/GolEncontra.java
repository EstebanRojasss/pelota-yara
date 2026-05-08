package com.forum.api.domain.evento.gol;

import com.forum.api.domain.evento.EventoHandler;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;

public class GolEncontra implements EventoHandler {

    @Override
    public void manejarEvento(Partido partido, EventoDelPartido evento) {
        Equipo equipoEvento = evento.getEquipo();

        Equipo equipoAumentarGol =
                equipoEvento.equals(partido.getEquipoLocal())
                        ? partido.getEquipoVisitante() : partido.getEquipoLocal();

        partido.aumentarMarcador(equipoAumentarGol);
    }
}
