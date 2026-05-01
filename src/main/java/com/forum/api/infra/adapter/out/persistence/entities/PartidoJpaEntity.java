package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "equipo_local")
    private EquipoEntityJpa equipoLocal;

    @OneToOne
    @JoinColumn(name = "equipo_visitante")
    private EquipoEntityJpa equipoVisitante;
    private Integer golVisitante;
    private Integer golLocal;
    private Integer minutoActual;
    @Column(unique = true)
    private Long fixtureId;
    private Long ligaId;

    public static PartidoJpaEntity fromDomain(Partido partido) {
        return new PartidoJpaEntity(
                partido.getId(),
                partido.getStatus(),
                EquipoEntityJpa.fromDomain(partido.getEquipoLocal()),
                EquipoEntityJpa.fromDomain(partido.getEquipoVisitante()),
                partido.getGolVisitante(),
                partido.getGolLocal(),
                partido.getMinutoBase(),
                partido.getFixtureId(),
                partido.getLigaId());
    }

    public Partido toDomainExistent() {
        return Partido.restore(id,
                status,
                equipoLocal.toDomainExistent(),
                equipoVisitante.toDomainExistent(),
                golLocal,
                golVisitante,
                minutoActual,
                fixtureId,
                ligaId
                );
    }

}

