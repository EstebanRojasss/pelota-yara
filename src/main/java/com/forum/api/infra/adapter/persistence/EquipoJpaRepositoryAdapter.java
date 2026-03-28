package com.forum.api.infra.adapter.persistence;

import com.forum.api.application.out.EquipoRepository;
import com.forum.api.domain.Equipo;
import com.forum.api.domain.Jugador;
import com.forum.api.infra.adapter.persistence.entities.EquipoEntityJpa;
import com.forum.api.infra.adapter.persistence.repository.EquipoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EquipoJpaRepositoryAdapter implements EquipoRepository {

    private final EquipoJpaRepository jpaRepository;

    public EquipoJpaRepositoryAdapter(EquipoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Equipo save(Equipo equipo) {
        return jpaRepository.save(EquipoEntityJpa.fromDomain(equipo)).toDomainExistent();
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Equipo> findEquipoById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Equipo> findAllEquipos() {
        return jpaRepository.findCompetenciasEquipos()
                .stream()
                .map(EquipoEntityJpa::toDomainExistent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Jugador> listarTodosLosJugadoresDelEquipo() {
        return List.of();
    }
}
