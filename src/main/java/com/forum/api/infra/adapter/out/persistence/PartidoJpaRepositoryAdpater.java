package com.forum.api.infra.adapter.out.persistence;

import com.forum.api.application.out.PartidoRepository;
import com.forum.api.domain.model.Partido;
import com.forum.api.infra.adapter.out.persistence.entities.PartidoJpaEntity;
import com.forum.api.infra.adapter.out.persistence.repository.PartidoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PartidoJpaRepositoryAdpater implements PartidoRepository {

    private final PartidoJpaRepository repository;

    public PartidoJpaRepositoryAdpater(PartidoJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Partido savePartido(Partido partido) {
        return repository.save(PartidoJpaEntity.fromDomain(partido)).toDomainExistent();
    }

    @Override
    public Optional<Partido> findPartidoById(Long id) {
        return repository
                .findById(id)
                .map(PartidoJpaEntity::toDomainExistent);
    }

    @Override
    public void deletePartido(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Partido> findPartidosByStatus(String statusPartido) {
        return repository.findByStatusEquals(statusPartido)
                .stream()
                .map(PartidoJpaEntity::toDomainExistent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Partido> findAllPartidos() {
        return repository
                .findAll()
                .stream()
                .map(PartidoJpaEntity::toDomainExistent)
                .collect(Collectors.toList());
    }
}
