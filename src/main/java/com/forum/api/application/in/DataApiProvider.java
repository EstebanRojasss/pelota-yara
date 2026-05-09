package com.forum.api.application.in;


import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.infra.adapter.out.dto.event.EventDataDto;

import java.util.List;

public interface DataApiProvider {
    List<FixtureData> proveerDatosFixture();
    List<TeamDataDto> proveerDatosEquipos();
    List<EventoDataDto> proveerEventosPartido();
}
