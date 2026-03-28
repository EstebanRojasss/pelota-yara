package com.forum.api.infra.adapter.persistence.entities;

import com.forum.api.domain.Partido;
import com.forum.api.domain.StatusPartido;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "partidos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PartidoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
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


    public static PartidoJpaEntity fromDomain(Partido partido){
        return new PartidoJpaEntity(
                partido.getId(),
                partido.getStatus(),
                EquipoEntityJpa.fromDomain(partido.getEquipoLocal()),
                EquipoEntityJpa.fromDomain(partido.getEquipoVisitante()),
                partido.getGolVisitante(),
                partido.getGolLocal(),
                partido.getMinutoActual(),
                partido.getMinutoAdicional1T(),
                partido.getMinutoAdicional2T()
        );
    }

    public Partido toDomainExistent(){
        return Partido.restore(id,
                status,
                equipoLocal.toDomainExistent(),
                equipoVisitante.toDomainExistent(),
                golVisitante,
                golLocal,
                minutoActual,
                minutoAdicional1T,
                minutoAdicional2T);
    }
}
