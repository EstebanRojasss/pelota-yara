package com.forum.api.infra.adapter.persistence.repository;

import com.forum.api.infra.adapter.persistence.entities.PartidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoJpaRepository extends JpaRepository<PartidoJpaEntity, Long> {

}
