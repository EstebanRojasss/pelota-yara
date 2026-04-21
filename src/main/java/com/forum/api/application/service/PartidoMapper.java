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



    public StatusPartido mapStatus(StatusPartidoFixture statusFixture) {
        return switch (statusFixture) {
            case FIRST_HALF, PENALTY_IN_PROGRES, SECOND_HALF, BREAK_TIME, EXTRA_TIME -> StatusPartido.EN_JUEGO;
            case HALF_TIME -> StatusPartido.MEDIO_TIEMPO;
            case MATCH_SUSPENDED -> StatusPartido.FINALIZADO;
        };
    }
}
