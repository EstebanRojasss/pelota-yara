package com.forum.api.application.service;

import com.forum.api.application.in.EquipoService;
import com.forum.api.application.out.EquipoRepository;
import com.forum.api.domain.model.Equipo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;

    public EquipoServiceImpl(EquipoRepository repository, JugadorRepository jugadorRepository) {
        this.equipoRepository = repository;
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public Equipo agregarNuevoEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public void eliminarEquipo(Long id) {

    }

    @Override
    public Optional<Equipo> cambiarDatosEquipo(Equipo equipo) {
        return null;
    }

    @Override
    public List<Equipo> listarTodosLosEquipos() {
        return repository.findAllEquipos();
    }

    @Override
    public Equipo encontrarEquipoPorId(Long id) {
        return null;
    }
}
