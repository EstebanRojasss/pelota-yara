package com.forum.api.application.service;

import com.forum.api.application.in.EquipoService;
import com.forum.api.application.in.PartidoService;
import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.application.out.PartidoRepository;
import com.forum.api.domain.exception.PartidoNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Partido;
import com.forum.api.domain.model.StatusPartido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartidoServiceImpl implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final EquipoService equipoService;

    public PartidoServiceImpl(PartidoRepository partidoRepository, EquipoService equipoService) {
        this.partidoRepository = partidoRepository;
        this.equipoService = equipoService;
    }

    public Partido encontrarPartido(Long id) {
        return partidoRepository.findPartidoById(id).orElseThrow(() -> new PartidoNotFoundException("El partido no se encuentra"));
    }

    public void borrarPartido(Long id) {
        try {
            partidoRepository.deletePartido(id);
        } catch (RuntimeException e) {
            throw new PartidoNotFoundException("El partido no se encuentra");
        }
    }

    public Partido guardarNuevoPartido(CrearPartidoCommand partidoCommand) {
        Equipo equipoLocal = equipoService.encontrarEquipoPorId(partidoCommand.equipoLocalId());
        Equipo equipoVisitante = equipoService.encontrarEquipoPorId(partidoCommand.equipoVisitanteId());
        Partido partido = Partido.create(equipoLocal, equipoVisitante);
        return partidoRepository.savePartido(partido);
    }

    @Transactional
    public Partido actualizarDatosDePartido(Partido datosPartidoActualizar) {
        return partidoRepository.savePartido(datosPartidoActualizar);
    }

    public List<Partido> encontrarTodosLosPartidosEnVivo() {
        StatusPartido enVivo = StatusPartido.EN_JUEGO;
        return partidoRepository.findPartidosByStatus(enVivo);
    }


    private Equipo resolverEquipo(TeamDataDto team) {

        Optional<Equipo> equipo = equipoService.buscarEquipoPorEquipoFixtureId(team.equipoFixtureId());

        if (equipo.isPresent()) {
            return equipo.get();
        } else {
            Equipo equipoPersistir = Equipo.create(team.nombre(), null, null, team.equipoFixtureId());
            return equipoService.agregarNuevoEquipo(equipoPersistir);
        }
    }

    public List<Partido> listarTodosLosPartidos() {
        return partidoRepository.findAllPartidos();
    }

    @Override
    public Optional<Partido> encontrarPartidoPorFixtureId(Long id) {
        return partidoRepository.findByFixtureId(id);
    }
}

