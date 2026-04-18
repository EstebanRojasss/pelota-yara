package com.forum.api.infra.adapter.out.dto.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Teams(
        @JsonProperty("home")
        HomeTeam home,
        @JsonProperty("away")
        AwayTeam away) {
}
