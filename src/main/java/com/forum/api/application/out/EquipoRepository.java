package com.forum.api.application.out;

import com.forum.api.domain.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository {
     Equipo save(Equipo var1);

     void delete(Long var1);

     Optional<Equipo> findEquipoById(Long var1);

     List<Equipo> findAllEquipos();
}

