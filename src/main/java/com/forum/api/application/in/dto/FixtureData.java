package com.forum.api.application.in.dto;


public record FixtureData(
        Long id,
        TeamDataDto local,
        TeamDataDto visitante,
        Integer golLocal,
        Integer golVisitante,
        Integer minuto,
        StatusPartidoFixture statusFixture,
        Integer minutoExtra,
        LigaDataDto liga
) {

}
