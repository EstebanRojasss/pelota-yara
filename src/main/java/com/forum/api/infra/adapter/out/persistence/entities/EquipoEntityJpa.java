package com.forum.api.infra.adapter.out.persistence.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apodo;
    private LocalDate fundacion;
    @Column(unique = true)
    private Long equipoFixtureId;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "equipo_competencias",
            joinColumns = @JoinColumn(name = "equipo_id")
    )
    @Column(name = "competencias")
    @Enumerated(value = EnumType.STRING)
    private Set<Competencias> competencias;

    private EquipoEntityJpa(Long id, String nombre, String apodo, Long equipoFixtureId){
        this.id = id;
        this.nombre = nombre;
        this.apodo = apodo;
        this.equipoFixtureId = equipoFixtureId;
    }

    public Equipo toDomainExistent() {
        return Equipo.restore(id,
                nombre,
                apodo,
                fundacion,
                equipoFixtureId);
    }

    public Equipo toNewDomain() {
        return Equipo.create(nombre,
                apodo,
                fundacion,
                equipoFixtureId);
    }

    public static EquipoEntityJpa fromDomain(Equipo equipo) {
        EquipoEntityJpa entityFromDomain = new EquipoEntityJpa();
        entityFromDomain.setId(equipo.getId());
        entityFromDomain.setNombre(equipo.getNombre());
        entityFromDomain.setApodo(equipo.getApodo());
        entityFromDomain.setFundacion(equipo.getFundacion());
        entityFromDomain.setEquipoFixtureId(equipo.getEquipoFixtureId());
        return entityFromDomain;
    }

    public static EquipoEntityJpa createNewEntity(String nombre, String apodo, LocalDate fundacion, Long equipoFixtureId){
        EquipoEntityJpa newEntity = new EquipoEntityJpa();
        newEntity.setId(null);
        newEntity.setNombre(nombre);
        newEntity.setApodo(apodo);
        newEntity.setFundacion(fundacion);
        newEntity.setEquipoFixtureId(equipoFixtureId);
        return newEntity;
    }

}

