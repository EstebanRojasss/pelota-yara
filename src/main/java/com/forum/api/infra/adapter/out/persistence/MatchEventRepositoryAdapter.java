package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.MatchEventRepository;
import com.forum.api.domain.model.MatchEvent;
import com.forum.api.infra.adapter.out.persistence.entities.MatchEventJpaEntity;
import com.forum.api.infra.adapter.out.persistence.repository.MatchEventJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MatchEventRepositoryAdapter implements MatchEventRepository {
    private final MatchEventJpaRepository repository;

    public MatchEventRepositoryAdapter(MatchEventJpaRepository repository) {
        this.repository = repository;
    }

    public MatchEvent saveMatchEvent(MatchEvent matchEvent) {
        return repository
                .save(MatchEventJpaEntity.fromDomain(matchEvent))
                .toDomain();
    }

    public void deleteMatchEvent(Long id) {
        repository.deleteById(id);
    }

    public Optional<MatchEvent> findMatchEventById(Long id) {
        return Optional.empty();
    }
}

