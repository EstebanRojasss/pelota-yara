package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.JugadorRepository;
import com.forum.api.domain.model.Jugador;
import com.forum.api.infra.adapter.out.persistence.entities.JugadorEntityJpa;
import com.forum.api.infra.adapter.out.persistence.repository.JugadorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JugadorJpaRepositoryAdapter
implements JugadorRepository {
    private final JugadorJpaRepository repository;

    public JugadorJpaRepositoryAdapter(JugadorJpaRepository repository) {
        this.repository = repository;
    }

    public Optional<Jugador> encontrarJugador(Long id) {
        return repository
                .findById(id)
                .map(JugadorEntityJpa::toDomainExistent);
    }

    public Jugador guardarJugador(Jugador jugador) {
        return repository
                .save(JugadorEntityJpa.fromDomain(jugador))
                .toDomainExistent();
    }

    public void borrarJugador(Long id) {
        this.repository.deleteById(id);
    }

    public List<Jugador> listarJugadoresPorEquipo(Long equipoId) {
        return repository
                .findAllJugadoresFromEquipo(equipoId)
                .stream()
                .map(JugadorEntityJpa::toDomainExistent)
                .collect(Collectors.toList());
    }
}

