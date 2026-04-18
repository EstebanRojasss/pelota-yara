package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forum.api.infra.adapter.out.dto.fixture.Fixture;
import com.forum.api.infra.adapter.out.dto.goals.Goals;
import com.forum.api.infra.adapter.out.dto.team.Teams;


@JsonIgnoreProperties(ignoreUnknown = true)
public record FixtureWrapper(
        @JsonProperty("fixture")
        Fixture fixture,
        @JsonProperty("teams")
        Teams teams,
        @JsonProperty("goals")
        Goals goals
) {


}
