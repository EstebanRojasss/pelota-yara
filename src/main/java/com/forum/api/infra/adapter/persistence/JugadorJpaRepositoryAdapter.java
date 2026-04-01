package com.forum.api.infra.adapter.persistence;

import com.forum.api.application.out.JugadorRepository;
import com.forum.api.domain.model.Jugador;
import com.forum.api.infra.adapter.persistence.entities.JugadorEntityJpa;
import com.forum.api.infra.adapter.persistence.repository.JugadorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class JugadorJpaRepositoryAdapter implements JugadorRepository {

    private final JugadorJpaRepository repository;

    public JugadorJpaRepositoryAdapter(JugadorJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Jugador> encontrarJugador(Long id) {
        return repository.findById(id)
                .map(JugadorEntityJpa::toDomainExistent);
    }

    @Override
    public Jugador guardarJugador(Jugador jugador) {
        return repository.save(JugadorEntityJpa.fromDomain(jugador))
                .toDomainExistent();
    }

    @Override
    public void borrarJugador(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Jugador> listarJugadoresPorEquipo(Long equipoId) {
        return repository.findAllJugadoresFromEquipo(equipoId)
                .stream()
                .map(JugadorEntityJpa::toDomainExistent)
                .collect(Collectors.toList());
    }
}
