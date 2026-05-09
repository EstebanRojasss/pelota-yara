package com.forum.api.infra.adapter.out.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forum.api.application.in.dto.evento.EventType;
import com.forum.api.application.in.dto.evento.EventoDataDto;

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

        public EventoDataDto map(){
                return new EventoDataDto(
                        time,
                        teamDto.map(),
                        playerDto.map(),
                        new EventType(
                                eventType,
                                detail
                        )
                );
        }
}
