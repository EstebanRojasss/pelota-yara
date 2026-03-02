package com.forum.api.domain;

import jakarta.persistence.*;

@Entity
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusPartido status;

    @OneToOne
    private Equipo equipoLocal;

    @OneToOne
    private Equipo equipoVisitante;
}
