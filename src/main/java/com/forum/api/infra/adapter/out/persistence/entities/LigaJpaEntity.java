package com.forum.api.infra.adapter.out.persistence.entities;


import com.forum.api.domain.model.Liga;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity(name = "ligas")
@AllArgsConstructor
@Data
public class LigaJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String nombre;
    private String pais;
    private final Long fixtureLigaId;
    private Integer temporada;


    public Liga toDomainExistent(){
        return Liga.restore(
                id,
                nombre,
                pais,
                fixtureLigaId,
                temporada
        );
    }

    public static LigaJpaEntity fromDomain(Liga liga){
        return new LigaJpaEntity(
                liga.getId(),
                liga.getNombre(),
                liga.getPais(),
                liga.getFixtureLigaId(),
                liga.getTemporada()
        );
    }


}
