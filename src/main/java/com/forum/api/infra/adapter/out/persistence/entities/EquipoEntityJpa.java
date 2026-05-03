package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.Equipo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private String pais;
    private Integer fundacion;
    @Column(unique = true)
    private Long equipoFixtureId;
    private String logoUrlEquipo;


    private EquipoEntityJpa(Long id, String nombre, String pais, Long equipoFixtureId){
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.equipoFixtureId = equipoFixtureId;
    }

    public Equipo toDomainExistent() {
        return Equipo.restore(id,
                nombre,
                pais,
                fundacion,
                equipoFixtureId,
                logoUrlEquipo
                );
    }

    public Equipo toNewDomain() {
        return Equipo.create(nombre,
                pais,
                fundacion,
                equipoFixtureId,
                logoUrlEquipo);
    }

    public static EquipoEntityJpa fromDomain(Equipo equipo) {
        EquipoEntityJpa entityFromDomain = new EquipoEntityJpa();
        entityFromDomain.setId(equipo.getId());
        entityFromDomain.setNombre(equipo.getNombre());
        entityFromDomain.setPais(equipo.getPais());
        entityFromDomain.setFundacion(equipo.getFundacion());
        entityFromDomain.setEquipoFixtureId(equipo.getEquipoFixtureId());
        entityFromDomain.setLogoUrlEquipo(equipo.getLogo());
        return entityFromDomain;
    }

    public static EquipoEntityJpa createNewEntity(String nombre, String apodo, Integer fundacion, Long equipoFixtureId){
        EquipoEntityJpa newEntity = new EquipoEntityJpa();
        newEntity.setId(null);
        newEntity.setNombre(nombre);
        newEntity.setPais(apodo);
        newEntity.setFundacion(fundacion);
        newEntity.setEquipoFixtureId(equipoFixtureId);
        return newEntity;
    }

}

