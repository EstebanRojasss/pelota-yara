package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.evento.EventoDelPartido;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eventos_del_partido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoDelPartidoJpaEntity {
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



    public EventoDelPartido toDomain() {
        return EventoDelPartido.restaurarEventoDelPartido(id,
                partido.toDomainExistent(),
                equipo.toDomainExistent(),
                jugador.toDomainExistent(),
                minuto
                );
    }

    public static MatchEventJpaEntity fromDomain(EventoDelPartido eventoDelPartido) {
        return new MatchEventJpaEntity(
                eventoDelPartido.getId(),
                PartidoJpaEntity.fromDomain(eventoDelPartido.getPartido()),
                EquipoEntityJpa.fromDomain(eventoDelPartido.getEquipo()),
                JugadorEntityJpa.fromDomain(eventoDelPartido.getJugador()),
                eventoDelPartido.getMinuto());
    }

}

