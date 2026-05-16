package com.forum.api.infra.adapter.out.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TimeEventDto(
        @JsonProperty("time")
        Integer time,
        @JsonProperty("extra")
        Integer extraTime
) {
}
