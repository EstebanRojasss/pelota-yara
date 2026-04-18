package com.forum.api.infra.adapter.out.dto.goals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Goals(
        @JsonProperty("home")
        Integer home,
        @JsonProperty("away")
        Integer away) {
}
