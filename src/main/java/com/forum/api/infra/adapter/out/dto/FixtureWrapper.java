package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FixtureWrapper {
    @JsonProperty("fixture")
    private Fixture fixture;
    @JsonProperty("teams")
    private Teams teams;
    @JsonProperty("goals")
    private Goals goals;

}
