package com.forum.api.application.service;
import com.forum.api.application.in.dto.evento.EventType;
import com.forum.api.application.in.dto.evento.EventoDataDto;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.evento.TipoEvento;
import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public EventoDelPartido toNewDomain(Equipo equipo, Jugador jugador, EventoDataDto eventData, StatusPartido statusPartido, Partido partido){
        return EventoDelPartido.crearEventoDelPartido(
                equipo,
                jugador,
                eventData.time().time(),
                mapEventTypeToDomain(eventData.eventType()),
                statusPartido,
                partido,
                eventData.time().extraTime()
        );
    }

    public TipoEvento mapEventTypeToDomain(EventType event){
        return new TipoEvento(event.type(), event.detail());
    }

}
