package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.EventoDelPartidoRepository;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.infra.adapter.out.persistence.entities.EventoDelPartidoJpaEntity;
import com.forum.api.infra.adapter.out.persistence.repository.EventoDelPartidoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EventoDelPartidoRepositoryAdapter implements EventoDelPartidoRepository {
    private final EventoDelPartidoJpaRepository repository;

    public EventoDelPartidoRepositoryAdapter(EventoDelPartidoJpaRepository repository) {
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

    public Optional<EventoDelPartido> findEventoDelPartidoById(Long id) {
        return repository.findById(id).map(EventoDelPartidoJpaEntity::toDomain);
    }

    @Override
    public List<EventoDelPartido> findAllEventos() {
        return repository.findAll()
                .stream()
                .map(EventoDelPartidoJpaEntity::toDomain)
                .toList();
    }
}

