package com.forum.api.application.service;

import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.LigaDataDto;
import com.forum.api.application.in.dto.StatusPartidoFixture;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Liga;
import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class PartidoMapper {



    public Partido toNewDomain(FixtureData fixtureData, Equipo local, Equipo visitante, Liga liga) {
        Integer minutoExtra = fixtureData.minutoExtra() != null ? fixtureData.minutoExtra() : 0;
        Integer minutoBase = solucionarMinuto(fixtureData);
        log.info("MINUTO BASE AL CREAR EL PARTIDO: {}", minutoBase);
        return Partido.createFromApi(
                local,
                visitante,
                fixtureData.golLocal(),
                fixtureData.golVisitante(),
                minutoBase,
                mapStatus(fixtureData.statusFixture()),
                fixtureData.id(),
                liga,
                minutoExtra
        );
    }


    public void actualizarDesdeFixture(FixtureData fixture, Partido partido) {

        if (!Objects.equals(fixture.minuto(), partido.getMinutoBase())) {
            partido.fijarBaseMinuto(fixture.minuto());
        }

        if(!Objects.equals(fixture.minutoExtra(), partido.getMinutoAdicional())){
            partido.setMinutoAdicional(fixture.minutoExtra());
        }

        partido.actualizar(
                mapStatus(fixture.statusFixture()),
                partido.getEquipoLocal(),
                partido.getEquipoVisitante(),
                fixture.golVisitante(),
                fixture.golLocal());
    }

    private Integer solucionarMinuto(FixtureData fixture) {
        return switch (fixture.statusFixture()) {
            case FIRST_HALF -> fixture.minuto() != null ? fixture.minuto() : 45;
            case SECOND_HALF -> fixture.minuto() != null ? fixture.minuto() : 90;
            default -> 0;
        };
    }


    public StatusPartido mapStatus(StatusPartidoFixture statusFixture) {
        return switch (statusFixture) {
            case NOT_STARTED -> StatusPartido.NO_INICIADO;
            case FIRST_HALF -> StatusPartido.PRIMER_TIEMPO;
            case SECOND_HALF -> StatusPartido.SEGUNDO_TIEMPO;
            case EXTRA_TIME -> StatusPartido.TIEMPO_EXTRA;
            case PENALTY_IN_PROGRES -> StatusPartido.TANDA_PENALES;
            case HALF_TIME -> StatusPartido.MEDIO_TIEMPO;
            case BREAK_TIME -> StatusPartido.DESCANSO_TIEMPO_EXTRA;

            case MATCH_SUSPENDED,
                 MATCH_FINISHED,
                 MATCH_FINISHED_AFTER_PENALTY,
                 MATCH_FINISHED_AFTER_EXTRA_TIME -> StatusPartido.FINALIZADO;
        };
    }

    public Liga mapLigaDtoToDomain(LigaDataDto dto){
        return Liga.create(
                dto.nombre(),
                dto.pais(),
                dto.id(),
                dto.temporada());
    }
}
