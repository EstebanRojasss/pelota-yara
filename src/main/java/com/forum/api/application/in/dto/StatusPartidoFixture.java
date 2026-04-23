package com.forum.api.application.in.dto;

import java.util.HashMap;
import java.util.Map;

public enum StatusPartidoFixture {
    FIRST_HALF("1H"),

    HALF_TIME("HT"),

    SECOND_HALF("2H"),

    EXTRA_TIME("ET"),

    BREAK_TIME("BK"),

    PENALTY_IN_PROGRES("P"),

    NOT_STARTED("NS"),

    MATCH_FINISHED("FT"),

    MATCH_SUSPENDED("SUSP");

    private final String shortValue;

    private static final Map<String, StatusPartidoFixture> STATUS_MAP = new HashMap<>();

    static {
        for(StatusPartidoFixture status : values()){
            STATUS_MAP.put(status.shortValue, status);
        }
    }

    StatusPartidoFixture(String shortValue) {
        this.shortValue = shortValue;
    }

    public static StatusPartidoFixture fromShortValue(String shortValue) {
        StatusPartidoFixture status = STATUS_MAP.get(shortValue);

        if(status == null){
            throw new IllegalArgumentException("Status desconocido " + shortValue);
        }

        return status;
    }
}
