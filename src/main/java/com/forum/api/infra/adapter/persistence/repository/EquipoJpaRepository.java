package com.forum.api.infra.adapter.persistence.repository;

import com.forum.api.infra.adapter.persistence.entities.EquipoEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface EquipoJpaRepository extends JpaRepository<EquipoEntityJpa, Long> {

    Optional<EquipoEntityJpa> findByNombre(String nombre);

    @Query("SELECT DISTINCT e FROM EquipoEntityJpa e LEFT JOIN FETCH e.competencias")
    List<EquipoEntityJpa> findCompetenciasEquipos();

}