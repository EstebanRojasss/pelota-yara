package com.forum.api.infra.adapter.persistence.entities;

import com.forum.api.domain.EventoPartido;
import com.forum.api.domain.MatchEvent;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MatchEvent")
public class MatchEventJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "partido")
    private PartidoJpaEntity partido;
    @OneToOne
    @JoinColumn(name = "equipo")
    private EquipoEntityJpa equipo;
    @OneToOne
    @JoinColumn(name = "jugador")
    private JugadorEntityJpa jugador;
    private Integer minuto;
    @Enumerated(EnumType.STRING)
    private EventoPartido eventoPartido;



    public MatchEvent toDomain(){
        return MatchEvent.restoreMatchEvent(
                id,
                partido.toDomainExistent(),
                equipo.toDomainExistent(),
                jugador.toDomainExistent(),
                minuto,
                eventoPartido);
    }

    public static MatchEventJpaEntity fromDomain(MatchEvent matchEvent){
        return new MatchEventJpaEntity(
                matchEvent.getId(),
                PartidoJpaEntity.fromDomain(matchEvent.getPartido()),
                EquipoEntityJpa.fromDomain(matchEvent.getEquipo()),
                JugadorEntityJpa.fromDomain(matchEvent.getJugador()),
                matchEvent.getMinuto(),
                matchEvent.getEventoPartido()
        );
    }
}
