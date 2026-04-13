package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "match_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchEventJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido")
    private PartidoJpaEntity partido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo")
    private EquipoEntityJpa equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador")
    private JugadorEntityJpa jugador;

    private Integer minuto;

    @Enumerated(value = EnumType.STRING)
    private EventoPartido eventoPartido;

    public MatchEvent toDomain() {
        return MatchEvent.restoreMatchEvent(id,
                partido.toDomainExistent(),
                equipo.toDomainExistent(),
                jugador.toDomainExistent(),
                minuto,
                eventoPartido);
    }

    public static MatchEventJpaEntity fromDomain(MatchEvent matchEvent) {
        return new MatchEventJpaEntity(
                matchEvent.getId(),
                PartidoJpaEntity.fromDomain(matchEvent.getPartido()),
                EquipoEntityJpa.fromDomain(matchEvent.getEquipo()),
                JugadorEntityJpa.fromDomain(matchEvent.getJugador()),
                matchEvent.getMinuto(),
                matchEvent.getEventoPartido());
    }

}

