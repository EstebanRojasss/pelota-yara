package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "partidos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartidoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private StatusPartido status;

    @ManyToOne
    @JoinColumn(name = "equipo_local")
    private EquipoEntityJpa equipoLocal;
    @ManyToOne
    @JoinColumn(name = "equipo_visitante")
    private EquipoEntityJpa equipoVisitante;
    private Integer golVisitante;
    private Integer golLocal;
    @Column(unique = true)
    private Long fixtureId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liga_id")
    private LigaJpaEntity liga;
    private Integer minutoBase;
    private Instant timeStampBase;

    public static PartidoJpaEntity fromDomain(Partido partido) {
        return new PartidoJpaEntity(
                partido.getId(),
                partido.getStatus(),
                EquipoEntityJpa.fromDomain(partido.getEquipoLocal()),
                EquipoEntityJpa.fromDomain(partido.getEquipoVisitante()),
                partido.getGolVisitante(),
                partido.getGolLocal(),
                partido.getFixtureId(),
                LigaJpaEntity.fromDomain(partido.getLiga()),
                partido.getMinutoBase(),
                partido.getTimeStampBase()
        );
    }

    public Partido toDomainExistent() {
        return Partido.restore(id,
                status,
                equipoLocal.toDomainExistent(),
                equipoVisitante.toDomainExistent(),
                golLocal,
                golVisitante,
                fixtureId,
                liga.toDomainExistent(),
                minutoBase,
                timeStampBase
        );
    }

}

