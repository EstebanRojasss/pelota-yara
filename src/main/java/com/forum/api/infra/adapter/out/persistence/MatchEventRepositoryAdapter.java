package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.MatchEventRepository;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.infra.adapter.out.persistence.entities.EventoDelPartidoJpaEntity;
import com.forum.api.infra.adapter.out.persistence.repository.MatchEventJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MatchEventRepositoryAdapter implements MatchEventRepository {
    private final MatchEventJpaRepository repository;

    public MatchEventRepositoryAdapter(MatchEventJpaRepository repository) {
        this.repository = repository;
    }

    public EventoDelPartido saveMatchEvent(EventoDelPartido eventoDelPartido) {
        return repository
                .save(EventoDelPartidoJpaEntity.fromDomain(eventoDelPartido))
                .toDomain();
    }

    public void deleteMatchEvent(Long id) {
        repository.deleteById(id);
    }

    public Optional<EventoDelPartido> findMatchEventById(Long id) {
        return repository.findById(id).map(EventoDelPartidoJpaEntity::toDomain);
    }
}

