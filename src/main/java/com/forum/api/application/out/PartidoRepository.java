package com.forum.api.application.out;

import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;

import java.util.List;
import java.util.Optional;

public interface PartidoRepository {
     Partido savePartido(Partido var1);

     Optional<Partido> findPartidoById(Long var1);

     void deletePartido(Long var1);

     List<Partido> findPartidosByStatus(StatusPartido var1);

     List<Partido> findAllPartidos();

     Optional<Partido> findByFixtureId(Long id);
}

