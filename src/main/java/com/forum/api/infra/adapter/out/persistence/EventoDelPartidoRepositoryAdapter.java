package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.EventoDelPartidoRepository;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.infra.adapter.out.persistence.entities.EventoDelPartidoJpaEntity;
import com.forum.api.infra.adapter.out.persistence.repository.MatchEventJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventoDelPartidoRepositoryAdapter implements EventoDelPartidoRepository {
    private final MatchEventJpaRepository repository;

    public EventoDelPartidoRepositoryAdapter(MatchEventJpaRepository repository) {
        this.repository = repository;
    }

    public EventoDelPartido saveEventoDelPartido(EventoDelPartido eventoDelPartido) {
        return repository
                .save(EventoDelPartidoJpaEntity.fromDomain(eventoDelPartido))
                .toDomain();
    }

    public void deleteEventoDelPartido(Long id) {
        repository.deleteById(id);
    }

    public Optional<EventoDelPartido> findMatchEventById(Long id) {
        return repository.findById(id).map(EventoDelPartidoJpaEntity::toDomain);
    }
}

