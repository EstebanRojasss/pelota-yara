package com.forum.api.domain.model.evento;

import com.forum.api.application.in.dto.evento.EventoDataDto;

public record EventKey(
        Integer minuto,
        Long jugadorApiId,
        String tipoEvento
) {

    public static EventKey from(EventoDataDto eventData){
        return new EventKey(eventData.time(),
                eventData.playerEvent().id(),
                eventData.eventType().type());
    }

}
