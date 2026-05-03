package com.forum.api.application.in;


import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.TeamDataDto;

import java.util.List;

public interface DataApiProvider {
    List<FixtureData> proveerDatosFixture();
    List<TeamDataDto> proveerDatosEquipos();
}
