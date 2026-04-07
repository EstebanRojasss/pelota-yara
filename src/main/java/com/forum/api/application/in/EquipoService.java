package com.forum.api.application.in;

import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;

import java.util.List;
import java.util.Optional;

public interface EquipoService {

     Equipo agregarNuevoEquipo(Equipo var1);

     void eliminarEquipo(Long var1);

     Optional<Equipo> cambiarDatosEquipo(Equipo var1);

     List<Equipo> listarTodosLosEquipos();

     Equipo encontrarEquipoPorId(Long var1);

     List<Jugador> listarJugadoresEquipo(Long var1);
}

