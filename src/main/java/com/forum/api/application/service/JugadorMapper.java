package com.forum.api.application.service;

import com.forum.api.application.in.dto.JugadorDataDto;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import org.springframework.stereotype.Component;


@Component
public class JugadorMapper {

    public Jugador toNewDomain(JugadorDataDto jugadorDataDto, Equipo equipo){
        return Jugador.create(
                jugadorDataDto.nombre(),
                jugadorDataDto.edad(),
                jugadorDataDto.id(),
                equipo
        );
    }

    public void actualizarSiHayCambios(JugadorDataDto jugadorDataDto, Jugador jugador, Equipo equipo){
        jugador.actualizar(
                jugador.getNombre(),
                jugadorDataDto.edad(),
                equipo
        );
    }

}
