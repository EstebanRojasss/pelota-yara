package com.forum.api.application.service;

import com.forum.api.application.in.PartidoService;
import com.forum.api.application.out.PartidoRepository;
import com.forum.api.domain.model.Partido;
import com.forum.api.domain.exception.PartidoNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;

    public PartidoServiceImpl(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
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
    public Partido guardarNuevoPartido(Partido partido) {
        Equipo equipoLocal = equipoRepository
                .findEquipoById(partido.getEquipoLocal().getId())
                .orElseThrow(() -> new EquipoNotFoundException("Error, el equipo no existe"));
        Equipo equipoVisitante = equipoRepository.findEquipoById(partido.getEquipoVisitante().getId())
                .orElseThrow(() -> new EquipoNotFoundException("Error, el equipo no existe"));

        return partidoRepository.savePartido(partido);
    }

    @Override
    public Partido actualizarDatosDePartido(Partido partido) {
        return null;
    }

    @Override
    public List<Partido> encontrarTodosLosPartidosEnVivo() {
        final String enVivo = StatusPartido.PRIMER_TIEMPO.name();
        
        return partidoRepository.findPartidosByStatus(enVivo);
    }

    @Override
    public List<Partido> listarTodosLosPartidos() {
        return List.of();
    }
}
