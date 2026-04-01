package com.forum.api.infra.adapter.persistence.entities;

import com.forum.api.domain.model.Competencias;
import com.forum.api.domain.model.Equipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "equipos")
@Getter
@NoArgsConstructor
@Setter
public class EquipoEntityJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private String apodo;
    private LocalDate fundacion;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "equipo_competencias")
    @Column(name = "competencias")
    @Enumerated(EnumType.STRING)
    private Set<Competencias> competencias;

    public Equipo toDomainExistent() {
        return Equipo.restore(
                id,
                nombre,
                apodo,
                fundacion,
                competencias

        );
    }

    public Equipo toNewDomain() {
        return Equipo.create(nombre,
                apodo,
                fundacion,
                competencias);
    }

    public static EquipoEntityJpa fromDomain(Equipo equipo) {
        EquipoEntityJpa entity = new EquipoEntityJpa();
        entity.setId(equipo.getId());
        entity.setNombre(equipo.getNombre());
        entity.setApodo(equipo.getApodo());
        entity.setFundacion(equipo.getFundacion());
        entity.setCompetencias(equipo.getCompetencias());
        return entity;
    }
}
