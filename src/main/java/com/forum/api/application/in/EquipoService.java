package com.forum.api.application.in;

import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EquipoService {

     Equipo agregarNuevoEquipo(Equipo eq);

     void eliminarEquipo(Long id);

     Optional<Equipo> cambiarDatosEquipo(Equipo equipo);

     List<Equipo> listarTodosLosEquipos();

     Equipo encontrarEquipoPorId(Long id);

     List<Jugador> listarJugadoresEquipo(Long id);

     Optional<Equipo>buscarEquipoPorId(Long id);

     Optional<Equipo>buscarEquipoPorEquipoFixtureId(Long equipoFixtureId);

     List<Equipo> agregarEquiposPorLigaDesdeApiExterna();

     Map<Long, Equipo> cacheEquipos();
}

