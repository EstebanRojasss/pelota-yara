package com.forum.api.infra.adapter.out.persistence.repository;

import com.forum.api.infra.adapter.out.persistence.entities.LigaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigaJpaRepository extends JpaRepository<LigaJpaEntity, Long> {

}
