package com.forum.api.infra.adapter.persistence;

import com.forum.api.application.out.PartidoRepository;
import com.forum.api.domain.model.Partido;
import com.forum.api.infra.adapter.persistence.entities.PartidoJpaEntity;
import com.forum.api.infra.adapter.persistence.repository.PartidoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
}
