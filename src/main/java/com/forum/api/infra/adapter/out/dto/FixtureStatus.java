package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FixtureStatus(
        @JsonProperty("long")
        String longStatus,
        @JsonProperty("elapsed")
        Integer elapsed) {
}
