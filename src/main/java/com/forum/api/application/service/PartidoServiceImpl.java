package com.forum.api.application.service;

import com.forum.api.application.in.PartidoService;
import com.forum.api.application.out.PartidoRepository;
import com.forum.api.domain.model.Partido;
import com.forum.api.domain.exception.PartidoNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoService equipoService;

    public PartidoServiceImpl(PartidoRepository partidoRepository, EquipoService equipoService) {
        this.partidoRepository = partidoRepository;
        this.equipoService = equipoService;
    }

    @Override
    public Partido encontrarPartido(Long id) {
        return partidoRepository.
                findPartidoById(id).
                orElseThrow(() -> new PartidoNotFoundException("El partido no se encuentra"));
    }

    @Override
    public void borrarPartido(Long id) {
        try {
            partidoRepository.deletePartido(id);
        } catch (RuntimeException e){
            throw new PartidoNotFoundException("El partido no se encuentra");
        }
    }

    @Override
    public Partido guardarNuevoPartido(CrearPartidoCommand partidoCommand) {
        Equipo equipoLocal = equipoService
                .encontrarEquipoPorId(partidoCommand.equipoLocalId());
        Equipo equipoVisitante = equipoService
                .encontrarEquipoPorId(partidoCommand.equipoVisitanteId());

        Partido partido = Partido.create(equipoLocal, equipoVisitante);

        return partidoRepository.savePartido(partido);
    }

    @Override
    public Partido actualizarDatosDePartido(Partido partido) {
        return null;
    }

    @Override
    public List<Partido> encontrarTodosLosPartidosEnVivo() {
        final String enVivo = StatusPartido.EN_JUEGO.name();
        return partidoRepository.findPartidosByStatus(enVivo);
    }

    @Override
    public List<Partido> listarTodosLosPartidos() {
        return partidoRepository.findAllPartidos();
    }
}
