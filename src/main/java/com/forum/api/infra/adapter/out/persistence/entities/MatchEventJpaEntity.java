package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.evento.TipoEventoPartido;
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
    private TipoEventoPartido tipoEventoPartido;

    public EventoDelPartido toDomain() {
        return EventoDelPartido.restoreMatchEvent(id,
                partido.toDomainExistent(),
                equipo.toDomainExistent(),
                jugador.toDomainExistent(),
                minuto,
                tipoEventoPartido);
    }

    public static MatchEventJpaEntity fromDomain(EventoDelPartido eventoDelPartido) {
        return new MatchEventJpaEntity(
                eventoDelPartido.getId(),
                PartidoJpaEntity.fromDomain(eventoDelPartido.getPartido()),
                EquipoEntityJpa.fromDomain(eventoDelPartido.getEquipo()),
                JugadorEntityJpa.fromDomain(eventoDelPartido.getJugador()),
                eventoDelPartido.getMinuto(),
                eventoDelPartido.getEventoPartido());
    }

}

