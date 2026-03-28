package com.forum.api.application.in;

import com.forum.api.domain.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {

    Equipo agregarNuevoEquipo(Equipo equipo);
    void eliminarEquipo(Long id);
    Optional<Equipo> cambiarDatosEquipo(Equipo equipo);
    List<Equipo> listarTodosLosEquipos();
    Equipo encontrarEquipoPorId(Long id);
}
