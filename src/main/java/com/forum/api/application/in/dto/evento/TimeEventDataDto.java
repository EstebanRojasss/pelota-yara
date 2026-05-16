package com.forum.api.application.in.dto.evento;

public record TimeEventDataDto(
        Integer time,
        Integer extraTime
) {

    public static TimeEventDataDto from(Integer time, Integer extraTime){
        return new TimeEventDataDto(
                time,
                extraTime
        );
    }
}
