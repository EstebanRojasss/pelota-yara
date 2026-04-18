package com.forum.api.infra.adapter.out.dto.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AwayTeam(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name) {
}
