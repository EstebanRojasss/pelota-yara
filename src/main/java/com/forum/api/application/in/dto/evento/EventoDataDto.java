package com.forum.api.application.in.dto.evento;

public record EventoDataDto(Integer time,
                            TeamEventDataDto teamEvent,
                            PlayerEventDataDto playerEvent,
                            EventType eventType) {

}
