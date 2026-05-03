package com.forum.api.infra.adapter.out.dto.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TeamDto(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name,
        @JsonProperty("logo")
        String urlLogo,
        @JsonProperty("country")
        String pais,
        @JsonProperty("founded")
        Integer fundacion
) {
}
