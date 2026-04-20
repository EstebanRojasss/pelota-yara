package com.forum.api.infra.adapter.out.persistence.repository;

import com.forum.api.infra.adapter.out.persistence.entities.EquipoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EquipoJpaRepository extends JpaRepository<EquipoEntityJpa, Long> {
    Optional<EquipoEntityJpa> findByNombre(String nombre);


}

