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
    private final EventoDelPartidoJpaRepository eventoRepository;

    public EventoDelPartidoRepositoryAdapter(EventoDelPartidoJpaRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EventoDelPartido saveEventoDelPartido(EventoDelPartido eventoDelPartido) {
        return eventoRepository
                .save(EventoDelPartidoJpaEntity.fromDomain(eventoDelPartido))
                .toDomain();
    }

    public void deleteEventoDelPartido(Long id) {
        eventoRepository.deleteById(id);
    }

    public Optional<EventoDelPartido> findEventoDelPartidoById(Long id) {
        return eventoRepository.findById(id).map(EventoDelPartidoJpaEntity::toDomain);
    }

    @Override
    public List<EventoDelPartido> findAllEventos() {
        return eventoRepository.findAll()
                .stream()
                .map(EventoDelPartidoJpaEntity::toDomain)
                .toList();
    }

    @Override
    public void saveEventosPorFase(List<EventoDelPartido> eventosDelPartido) {
        eventoRepository.saveAll(
                eventosDelPartido.
                        stream().
                        map(EventoDelPartidoJpaEntity::fromDomain).
                        toList()
        );
    }
}

