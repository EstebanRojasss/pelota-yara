package com.forum.api.infra.adapter.persistence;

import com.forum.api.application.out.MatchEventRepository;
import com.forum.api.domain.model.MatchEvent;
import com.forum.api.infra.adapter.persistence.entities.MatchEventJpaEntity;
import com.forum.api.infra.adapter.persistence.repository.MatchEventJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class MatchEventRepositoryAdapter implements MatchEventRepository {
    private final MatchEventJpaRepository repository;

    public MatchEventRepositoryAdapter(MatchEventJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public MatchEvent saveMatchEvent(MatchEvent matchEvent) {
        return repository.save(MatchEventJpaEntity.fromDomain(matchEvent)).toDomain();
    }

    @Override
    public void deleteMatchEvent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<MatchEvent> findMatchEventById(Long id) {
        return Optional.empty();
    }
}
