package com.forum.api.application.out;

import com.forum.api.domain.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository {
     Equipo save(Equipo equipo);

     void delete(Long id);

     Optional<Equipo> findEquipoById(Long id);

     List<Equipo> findAllEquipos();

     Optional<Equipo> findEquipoByEquipoFixtureId(Long equipoFixtureId);

     List<Equipo> saveAllEquipos(List<Equipo> equipos);
}

