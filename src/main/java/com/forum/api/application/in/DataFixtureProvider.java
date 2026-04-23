package com.forum.api.application.in;


import com.forum.api.application.in.dto.FixtureData;

import java.util.List;

public interface DataFixtureProvider {
    List<FixtureData> proveerDatosFixture();
}
