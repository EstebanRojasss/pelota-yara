package com.forum.api.infra.adapter.out.dto.fixture;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FixtureStatus(
        @JsonProperty("short")
        String shortStatus,
        @JsonProperty("elapsed")
        Integer elapsed,
        @JsonProperty("extra")
        Integer extraMinute) {
}
