package com.forum.api.infra.adapter.out.persistence.repository;

import com.forum.api.infra.adapter.out.persistence.entities.MatchEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchEventJpaRepository extends JpaRepository<MatchEventJpaEntity, Long> {
}

