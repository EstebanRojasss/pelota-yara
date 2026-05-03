package com.forum.api.application.service;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.EquipoService;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.application.out.EquipoRepository;
import com.forum.api.application.out.JugadorRepository;
import com.forum.api.domain.exception.EquipoNotFoundException;
import com.forum.api.domain.exception.JugadorNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;
    private final JugadorRepository jugadorRepository;
    private final DataApiProvider equiposDesdeApiProvider;
    private final EquipoMapper equipoMapper;
    private final Map<Long, Equipo> cacheEquipos = new HashMap<>();

    public EquipoServiceImpl(EquipoRepository repository, JugadorRepository jugadorRepository, DataApiProvider equiposDesdeApiProvider, EquipoMapper equipoMapper) {
        this.equipoRepository = repository;
        this.jugadorRepository = jugadorRepository;
        this.equiposDesdeApiProvider = equiposDesdeApiProvider;
        this.equipoMapper = equipoMapper;
    }

    public Equipo agregarNuevoEquipo(Equipo eq) {
        return this.equipoRepository.save(eq);
    }

    public void eliminarEquipo(Long id) {
        try {
            this.equipoRepository.delete(id);
        } catch (RuntimeException e) {
            throw new JugadorNotFoundException(e.getMessage());
        }
    }

    public Optional<Equipo> cambiarDatosEquipo(Equipo eq) {
        return null;
    }

    public List<Equipo> listarTodosLosEquipos() {
        return equipoRepository.findAllEquipos();
    }

    public Equipo encontrarEquipoPorId(Long id) {
        return equipoRepository.findEquipoById(id).orElseThrow(() -> new EquipoNotFoundException("El equipo no encontrado."));
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

    @Transactional
    public List<Equipo> agregarEquiposPorLigaDesdeApiExterna() {
        return equiposDesdeApiProvider
                .proveerDatosEquipos()
                .stream()
                .map(this::procesarDatosApiExterna)
                .toList();
    }

    @Override
    public Map<Long, Equipo> cacheEquipos() {
        return cacheEquipos;
    }

    private Equipo procesarDatosApiExterna(TeamDataDto team) {
        Equipo equipo = cacheEquipos.get(team.id());

        if (equipo == null) {
            equipo = equipoRepository.save(
                    equipoMapper.toNewDomain(team)
            );
            cacheEquipos.put(team.id(), equipo);

        } else{
            equipoMapper.actualizarDesdeApiExterna(team, equipo);
        }

        return equipo;
    }
}

