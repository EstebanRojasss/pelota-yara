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
    private Integer minutoAdicional1T;
    private Integer minutoAdicional2T;
    private Long fixtureId;

    public static PartidoJpaEntity fromDomain(Partido partido) {
        return new PartidoJpaEntity(
                partido.getId(),
                partido.getStatus(),
                EquipoEntityJpa.fromDomain(partido.getEquipoLocal()),
                EquipoEntityJpa.fromDomain(partido.getEquipoVisitante()),
                partido.getGolVisitante(),
                partido.getGolLocal(),
                partido.getMinutoActual(),
                partido.getMinutoAdicional1T(),
                partido.getMinutoAdicional2T(),
                partido.getFixtureId());
    }

    public Partido toDomainExistent() {
        return Partido.restore(id,
                status,
                equipoLocal.toDomainExistent(),
                equipoVisitante.toDomainExistent(),
                golVisitante,
                golLocal,
                minutoActual,
                minutoAdicional1T,
                minutoAdicional2T,
                fixtureId);
    }

}

