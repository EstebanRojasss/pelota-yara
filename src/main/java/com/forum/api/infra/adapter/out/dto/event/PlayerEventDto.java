package com.forum.api.infra.adapter.out.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forum.api.application.in.dto.evento.PlayerEventDataDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PlayerEventDto(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name) {
        public PlayerEventDataDto map(){
                return new PlayerEventDataDto(
                        id,
                        name
                );
        }
}
