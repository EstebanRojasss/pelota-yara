package com.forum.api.infra.adapter.out.dto.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record League(
        @JsonProperty("id")
        Long id
) {
}
