package com.forum.api.infra.adapter.out.persistence.repository;

import com.forum.api.infra.adapter.out.persistence.entities.EventoDelPartidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoDelPartidoJpaRepository extends JpaRepository<EventoDelPartidoJpaEntity, Long> {
}

