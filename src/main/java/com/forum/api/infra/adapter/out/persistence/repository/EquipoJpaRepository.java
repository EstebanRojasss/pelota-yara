package com.forum.api.infra.adapter.out.persistence.repository;

import com.forum.api.infra.adapter.out.persistence.entities.EquipoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EquipoJpaRepository extends JpaRepository<EquipoEntityJpa, Long> {
    Optional<EquipoEntityJpa> findByNombre(String var1);

    @Query(value = "SELECT DISTINCT e FROM EquipoEntityJpa e LEFT JOIN FETCH e.competencias")
    List<EquipoEntityJpa> findCompetenciasEquipos();
}

