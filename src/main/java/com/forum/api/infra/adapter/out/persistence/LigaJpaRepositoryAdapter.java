package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.LigaRepository;
import com.forum.api.domain.model.Liga;
import com.forum.api.infra.adapter.out.persistence.entities.LigaJpaEntity;
import com.forum.api.infra.adapter.out.persistence.repository.LigaJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class LigaJpaRepositoryAdapter implements LigaRepository {

    private final LigaJpaRepository ligaJpaRepository;

    public LigaJpaRepositoryAdapter(LigaJpaRepository ligaJpaRepository) {
        this.ligaJpaRepository = ligaJpaRepository;
    }

    @Override
    public Liga save(Liga liga) {
        return ligaJpaRepository.save(LigaJpaEntity.fromDomain(liga)).toDomainExistent();
    }
}
