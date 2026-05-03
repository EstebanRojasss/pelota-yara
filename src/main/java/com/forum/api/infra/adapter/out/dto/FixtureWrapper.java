package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.StatusPartidoFixture;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.infra.adapter.out.dto.fixture.Fixture;
import com.forum.api.infra.adapter.out.dto.goals.Goals;
import com.forum.api.infra.adapter.out.dto.team.League;
import com.forum.api.infra.adapter.out.dto.team.Teams;


@JsonIgnoreProperties(ignoreUnknown = true)
public record FixtureWrapper(
        @JsonProperty("fixture")
        Fixture fixture,
        @JsonProperty("teams")
        Teams teams,
        @JsonProperty("goals")
        Goals goals,
        @JsonProperty("league")
        League league
) {
        public static FixtureData map(FixtureWrapper wrapper){
                return new FixtureData(
                        wrapper.fixture.id(),
                        TeamDataDto.map(
                                wrapper.teams.home().id(),
                                wrapper.teams.home().name(),
                                wrapper.teams.home().urlLogo(),
                                wrapper.teams.home().pais(),
                                wrapper.teams.home().fundacion()),
                        TeamDataDto.map(
                                wrapper.teams.away().id(),
                                wrapper.teams.away().name(),
                                wrapper.teams.away().urlLogo(),
                                wrapper.teams.away().pais(),
                                wrapper.teams.away().fundacion()),
                        wrapper.goals.home(),
                        wrapper.goals.away(),
                        wrapper.fixture.status().elapsed(),
                        StatusPartidoFixture.fromShortValue(wrapper.fixture.status().shortStatus()),
                        wrapper.fixture.status().elapsed(),
                        wrapper.league().id()
                        );
        }

}
