package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.EquipoRepository;
import com.forum.api.domain.model.Equipo;
import com.forum.api.infra.adapter.out.persistence.entities.EquipoEntityJpa;
import com.forum.api.infra.adapter.out.persistence.repository.EquipoJpaRepository;
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

    public Equipo save(Equipo equipo) {
        return jpaRepository
                .save(EquipoEntityJpa.fromDomain(equipo))
                .toDomainExistent();
    }

    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    public Optional<Equipo> findEquipoById(Long id) {
        return jpaRepository
                .findById(id)
                .map(EquipoEntityJpa::toDomainExistent);
    }

    public List<Equipo> findAllEquipos() {
        return jpaRepository
                .findCompetenciasEquipos()
                .stream()
                .map(EquipoEntityJpa::toDomainExistent)
                .collect(Collectors.toList());
    }
}

