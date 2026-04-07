package com.forum.api.infra.adapter.out.persistence.repository;

import com.forum.api.infra.adapter.out.persistence.entities.JugadorEntityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface JugadorJpaRepository extends JpaRepository<JugadorEntityJpa, Long> {

    @Query(value = "SELECT DISTINCT j FROM JugadorEntityJpa j LEFT JOIN FETCH j.equipo e WHERE e.id = :id")
    Set<JugadorEntityJpa> findAllJugadoresFromEquipo(@Param(value = "id") Long id);

}

