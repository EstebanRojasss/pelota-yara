package com.forum.api.application.service;

import com.forum.api.application.in.EquipoService;
import com.forum.api.application.out.EquipoRepository;
import com.forum.api.application.out.JugadorRepository;
import com.forum.api.domain.exception.EquipoNotFoundException;
import com.forum.api.domain.exception.JugadorNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl
implements EquipoService {
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;

    public EquipoServiceImpl(EquipoRepository repository, JugadorRepository jugadorRepository) {
        this.equipoRepository = repository;
        this.jugadorRepository = jugadorRepository;
    }

    public Equipo agregarNuevoEquipo(Equipo equipo) {
        return this.equipoRepository.save(equipo);
    }

    public void eliminarEquipo(Long id) {
        try {
            this.equipoRepository.delete(id);
        }
        catch (RuntimeException e) {
            throw new JugadorNotFoundException(e.getMessage());
        }
    }

    public Optional<Equipo> cambiarDatosEquipo(Equipo equipo) {
        return null;
    }

    public List<Equipo> listarTodosLosEquipos() {
        return this.equipoRepository.findAllEquipos();
    }

    public Equipo encontrarEquipoPorId(Long id) {
        return (Equipo)this.equipoRepository.findEquipoById(id).orElseThrow(() -> new EquipoNotFoundException("El equipo no encontrado."));
    }

    public List<Jugador> listarJugadoresEquipo(Long id) {
        return this.jugadorRepository.listarJugadoresPorEquipo(id);
    }

    @Override
    public Optional<Equipo> buscarEquipoPorId(Long id) {
        return equipoRepository.findEquipoById(id);
    }

    @Override
    public Optional<Equipo> buscarEquipoPorEquipoFixtureId(Long equipoFixtureId) {
        return equipoRepository.findEquipoByEquipoFixtureId(equipoFixtureId);
    }
}

