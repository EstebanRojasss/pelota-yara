package com.forum.api.infra.adapter.out.persistence.entities;

import com.forum.api.domain.model.Jugador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="jugadores")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class JugadorEntityJpa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer edad;
    private String nacionalidad;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="equipo_id")
    private EquipoEntityJpa equipo;

    public Jugador toDomainExistent() {
        return Jugador.restore(id,
                nombre,
                edad,
                nacionalidad,
                equipo.toDomainExistent());
    }

    public static JugadorEntityJpa fromDomain(Jugador jugador) {
        JugadorEntityJpa entity = new JugadorEntityJpa();
        entity.setId(jugador.getId());
        entity.setNombre(jugador.getNombre());
        entity.setEdad(jugador.getEdad());
        entity.setNacionalidad(jugador.getNacionalidad());
        entity.setEquipo(EquipoEntityJpa.fromDomain(jugador.getEquipo()));
        return entity;
    }
}

