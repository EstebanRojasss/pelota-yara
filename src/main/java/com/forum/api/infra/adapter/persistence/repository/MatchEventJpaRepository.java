package com.forum.api.infra.adapter.persistence.repository;

import com.forum.api.infra.adapter.persistence.entities.MatchEventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchEventJpaRepository extends JpaRepository<MatchEventJpaEntity, Long> {
}
