package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Fixture(
        @JsonProperty("id")
        Long id,
        @JsonProperty("status")
        FixtureStatus status) {

}
