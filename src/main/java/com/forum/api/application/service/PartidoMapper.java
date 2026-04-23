package com.forum.api.application.service;

import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.StatusPartidoFixture;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.springframework.stereotype.Component;

@Component
public class PartidoMapper {

    public PartidoMapper() {
    }

    public Partido toNewDomain(FixtureData fixtureData, Equipo local, Equipo visitante) {
        return Partido.createFromApi(
                local,
                visitante,
                fixtureData.golLocal(),
                fixtureData.golVisitante(),
                fixtureData.minuto(),
                mapStatus(fixtureData.statusFixture()),
                fixtureData.id()
        );
    }

    public void actualizarDesdeFixture(FixtureData fixture, Partido partido){
        partido.actualizar(
                mapStatus(fixture.statusFixture()),
                partido.getEquipoLocal(),
                partido.getEquipoVisitante(),
                fixture.golVisitante(),
                fixture.golLocal(),
                fixture.minuto(),
                0,
                0);
    }


    public StatusPartido mapStatus(StatusPartidoFixture statusFixture) {
        return switch (statusFixture) {
            case NOT_STARTED -> StatusPartido.NO_INICIADO;
            case FIRST_HALF -> StatusPartido.PRIMER_TIEMPO;
            case SECOND_HALF -> StatusPartido.SEGUNDO_TIEMPO;
            case EXTRA_TIME -> StatusPartido.TIEMPO_EXTRA;
            case PENALTY_IN_PROGRES -> StatusPartido.TANDA_PENALES;
            case HALF_TIME -> StatusPartido.MEDIO_TIEMPO;

            case MATCH_SUSPENDED,
                 MATCH_FINISHED,
                 MATCH_FINISHED_AFTER_PENALTY,
                 MATCH_FINISHED_AFTER_EXTRA_TIME -> StatusPartido.FINALIZADO;
        };
    }
}
