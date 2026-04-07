package com.forum.api.infra.adapter.out.persistence.repository;

import com.forum.api.domain.model.StatusPartido;
import com.forum.api.infra.adapter.out.persistence.entities.PartidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidoJpaRepository extends JpaRepository<PartidoJpaEntity, Long> {
    List<PartidoJpaEntity> findByStatusEquals(StatusPartido status);
}

