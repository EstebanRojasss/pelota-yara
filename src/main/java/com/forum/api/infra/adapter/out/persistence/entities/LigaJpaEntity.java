package com.forum.api.infra.adapter.out.persistence.entities;


import com.forum.api.domain.model.Liga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "ligas")
@AllArgsConstructor
@Data
public class LigaJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String nombre;
    private String pais;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "liga")
    private Set<EquipoEntityJpa> equipos;

    private final Long fixtureLigaId;
    private Integer temporada;


    public Liga toDomainExistent(){
        return new Liga(
                id,
                nombre,
                pais,
                equipos.stream().map(EquipoEntityJpa::toDomainExistent).collect(Collectors.toSet()),
                fixtureLigaId,
                temporada
        );
    }

    public static LigaJpaEntity fromDomain(Liga liga){
        return new LigaJpaEntity(
                liga.getId(),
                liga.getNombre(),
                liga.getPais(),
                liga.getEquipos().stream().map(EquipoEntityJpa::fromDomain).collect(Collectors.toSet()),
                liga.getFixtureLigaId(),
                liga.getTemporada()
        );
    }


}
