package com.forum.api.infra.adapter.out.dto.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forum.api.application.in.dto.evento.TeamEventDataDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TeamEventDto(
        @JsonProperty("id")
        Long id,
        @JsonProperty("name")
        String name
) {

        public TeamEventDataDto map(){
                return new TeamEventDataDto(
                        id,
                        name
                );
        }
}
