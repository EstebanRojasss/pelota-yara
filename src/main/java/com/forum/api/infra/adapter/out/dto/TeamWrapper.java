package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.infra.adapter.out.dto.team.TeamDto;
import com.forum.api.infra.adapter.out.dto.team.VenueDto;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TeamWrapper(
        @JsonProperty("team")
        TeamDto team,
        @JsonProperty("venue")
        VenueDto venue
) {

    public static TeamDataDto map(TeamWrapper wrapper){
        return new TeamDataDto(
                wrapper.team().id(),
                wrapper.team.name(),
                wrapper.team.urlLogo(),
                wrapper.team.pais(),
                wrapper.team.fundacion()
        );
    }

}
