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

import java.util.List;

@Service
public class PartidoServiceImpl
implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final EquipoService equipoService;

    public PartidoServiceImpl(PartidoRepository partidoRepository, EquipoService equipoService) {
        this.partidoRepository = partidoRepository;
        this.equipoService = equipoService;
    }

    public Partido encontrarPartido(Long id) {
        return (Partido)this.partidoRepository.findPartidoById(id).orElseThrow(() -> new PartidoNotFoundException("El partido no se encuentra"));
    }

    public void borrarPartido(Long id) {
        try {
            this.partidoRepository.deletePartido(id);
        }
        catch (RuntimeException e) {
            throw new PartidoNotFoundException("El partido no se encuentra");
        }
    }

    public Partido guardarNuevoPartido(CrearPartidoCommand partidoCommand) {
        Equipo equipoLocal = this.equipoService.encontrarEquipoPorId(partidoCommand.equipoLocalId());
        Equipo equipoVisitante = this.equipoService.encontrarEquipoPorId(partidoCommand.equipoVisitanteId());
        Partido partido = Partido.create((Equipo)equipoLocal, (Equipo)equipoVisitante);
        return this.partidoRepository.savePartido(partido);
    }

    public Partido actualizarDatosDePartido(Partido datosPartidoActualizar) {
        this.partidoRepository.findPartidoById(datosPartidoActualizar.getId()).orElseThrow(() -> new PartidoNotFoundException("Partido no encontrado."));
        return this.partidoRepository.savePartido(datosPartidoActualizar);
    }

    public List<Partido> encontrarTodosLosPartidosEnVivo() {
        StatusPartido enVivo = StatusPartido.EN_JUEGO;
        return this.partidoRepository.findPartidosByStatus(enVivo);
    }

    public List<Partido> listarTodosLosPartidos() {
        return this.partidoRepository.findAllPartidos();
    }
}

