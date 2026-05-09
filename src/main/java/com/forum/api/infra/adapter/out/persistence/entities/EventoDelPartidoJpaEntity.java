package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.evento.TipoEvento;
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
    @JoinColumn(name = "id_equipo")
    private EquipoEntityJpa equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador")
    private JugadorEntityJpa jugador;

    private Integer minuto;
    @Column(name = "tipo_evento")
    private String tipoEvento;
    @Column(name = "detalle_evento")
    private String detalleEvento;



    public EventoDelPartido toDomain() {
        return EventoDelPartido.restaurarEventoDelPartido(id,
                equipo.toDomainExistent(),
                jugador.toDomainExistent(),
                minuto,
                new TipoEvento(tipoEvento, detalleEvento)
                );
    }

    public static EventoDelPartidoJpaEntity fromDomain(EventoDelPartido eventoDelPartido) {
        return new EventoDelPartidoJpaEntity(
                eventoDelPartido.getId(),
                EquipoEntityJpa.fromDomain(eventoDelPartido.getEquipo()),
                JugadorEntityJpa.fromDomain(eventoDelPartido.getJugador()),
                eventoDelPartido.getMinuto(),
                eventoDelPartido.getTipo().tipo(),
                eventoDelPartido.getTipo().detalleEvento());
    }

}

