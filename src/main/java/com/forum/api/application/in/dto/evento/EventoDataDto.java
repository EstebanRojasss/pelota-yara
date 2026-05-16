package com.forum.api.application.in.dto.evento;

public record EventoDataDto(TimeEventDataDto time,
                            TeamEventDataDto teamEvent,
                            PlayerEventDataDto playerEvent,
                            EventType eventType) {

}
