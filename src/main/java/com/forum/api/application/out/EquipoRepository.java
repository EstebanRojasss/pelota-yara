package com.forum.api.application.out;

import com.forum.api.domain.Equipo;
import com.forum.api.domain.Jugador;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository {
    Equipo save (Equipo equipo);
    void delete(Long id);
    Optional<Equipo> findEquipoById(Long id);
    List<Equipo> findAllEquipos();

    List<Jugador>listarTodosLosJugadoresDelEquipo();

}
