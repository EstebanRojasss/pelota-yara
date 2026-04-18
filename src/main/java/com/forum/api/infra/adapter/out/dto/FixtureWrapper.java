package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


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
