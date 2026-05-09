package com.forum.api.infra.adapter.out.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EventData(
        @JsonProperty("time")
        Integer time,
        @JsonProperty("team")
        TeamEventDto teamDto,
        @JsonProperty("player")
        PlayerEventDto playerDto,
        @JsonProperty("type")
        String eventType,
        @JsonProperty("detail")
        String detail) {
}
