package com.forum.api.application.in.command;

public record CrearPartidoCommand(Long equipoLocalId, Long equipoVisitanteId) {

    public CrearPartidoCommand(Long equipoLocalId, Long equipoVisitanteId) {
        this.equipoLocalId = equipoLocalId;
        this.equipoVisitanteId = equipoVisitanteId;
    }

    public Long equipoLocalId() {
        return this.equipoLocalId;
    }

    public Long equipoVisitanteId() {
        return this.equipoVisitanteId;
    }
}

