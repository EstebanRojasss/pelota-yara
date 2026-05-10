package com.forum.api.infra.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forum.api.application.in.dto.JugadorDataDto;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.infra.adapter.out.dto.player.PlayerDto;
import com.forum.api.infra.adapter.out.dto.player.PlayerTeamDto;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PlayerWrapper(
        @JsonProperty("team")
        PlayerTeamDto team,
        @JsonProperty("players")
        List<PlayerDto> players
) {

    public List<JugadorDataDto> mapFromApiData() {
        return
                players.
                        stream().
                        map(p -> JugadorDataDto.map(
                                        p.id(),
                                        p.name(),
                                        p.age(),
                                        TeamDataDto.map(
                                                team().id(),
                                                team.name(),
                                                team.urlLogo()
                                        )
                                )
                        ).toList();
    }
}
