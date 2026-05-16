package com.forum.api.domain.model.evento;


public record EventKey(
        Integer minuto,
        Long jugadorApiId,
        String tipoEvento
) {

    public static EventKey from(EventoDelPartido evento){
        return new EventKey(evento.getMinuto(),
                evento.getJugador().getId(),
                evento.getTipo().tipo());
    }

}
