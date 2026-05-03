package com.forum.api.application.service;

import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.domain.model.Equipo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@NoArgsConstructor
public class EquipoMapper {

    public Equipo toNewDomain(TeamDataDto teamDto) {
        return Equipo.create(teamDto.nombre(),
                teamDto.pais(),
                teamDto.fundacion(),
                teamDto.id(),
                teamDto.logo());
    }

    public void actualizarDesdeApiExterna(TeamDataDto team,Equipo equipo) {
        if (Objects.equals(equipo.getNombre(), team.nombre())
                || Objects.equals(team.logo(), equipo.getLogo())
                || Objects.equals(team.fundacion(), equipo.getFundacion())) {
            equipo.actualizar(
                    team.nombre(),
                    team.pais(),
                    team.fundacion(),
                    team.logo()
            );
        }

    }


}
