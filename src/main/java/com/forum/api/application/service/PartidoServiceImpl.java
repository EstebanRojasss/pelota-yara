package com.forum.api.application.service;

import com.forum.api.application.in.*;
import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.out.PartidoRepository;
import com.forum.api.domain.exception.PartidoNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Liga;
import com.forum.api.domain.model.partido.Partido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class PartidoServiceImpl implements PartidoService {
    private final PartidoRepository partidoRepository;
    private final EquipoService equipoService;
    private final DataApiProvider fixtureProvider;
    private final PartidoMapper partidoMapper;
    private final Map<Long, Partido> cachePartidos = new HashMap<>();
    private final LigaService ligaService;
    private final EventoDelPartidoService eventoDelPartidoService;


    public PartidoServiceImpl(PartidoRepository partidoRepository, EquipoService equipoService, DataApiProvider fixtureProvider, PartidoMapper partidoMapper, LigaService ligaService, EventoDelPartidoService eventoDelPartidoService) {
        this.partidoRepository = partidoRepository;
        this.equipoService = equipoService;
        this.fixtureProvider = fixtureProvider;
        this.partidoMapper = partidoMapper;
        this.ligaService = ligaService;
        this.eventoDelPartidoService = eventoDelPartidoService;
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

    public Partido guardarPartido(CrearPartidoCommand partidoCommand) {
        Equipo equipoLocal = equipoService.encontrarEquipoPorId(partidoCommand.equipoLocalId());
        Equipo equipoVisitante = equipoService.encontrarEquipoPorId(partidoCommand.equipoVisitanteId());
        Partido partido = Partido.createFromLocal(equipoLocal, equipoVisitante);
        return partidoRepository.savePartido(partido);
    }


    public Partido actualizarDatosDePartido(Partido datosPartidoActualizar) {
        return partidoRepository.savePartido(datosPartidoActualizar);
    }


    public List<Partido> partidosEnVivo(){
        return cachePartidos.values().stream().toList();
    }

    @Transactional
    public List<Partido> encontrarTodosLosPartidosEnVivo() {
        return fixtureProvider.
                proveerDatosFixture().
                stream().
                map(this::guardarOActualizarPartido).
                toList();
    }

    private Partido guardarOActualizarPartido(FixtureData fixture) {
        Equipo local = equipoService.resolverExistenciaEquipo(fixture.local());
        Equipo visitante = equipoService.resolverExistenciaEquipo(fixture.visitante());
        Liga liga = ligaService.resolverExistenciaLiga(fixture.liga());

        Partido partido = cachePartidos.get(fixture.id());

        if (partido == null) {

            partido = partidoRepository.savePartido(
                    partidoMapper.toNewDomain(fixture, local, visitante, liga)
            );
            cachePartidos.put(fixture.id(), partido);

        } else if (actualizarSiHayCambios(fixture, partido)) {
            partidoMapper.actualizarDesdeFixture(fixture, partido);
        }

        return partido;
    }

    private boolean actualizarSiHayCambios(FixtureData fixture, Partido partido) {
        boolean huboCambios = false;
        huboCambios |= existenCambios(partido::getStatus, partido::setStatus, partidoMapper.mapStatus(fixture.statusFixture()));
        huboCambios |= existenCambios(partido::getGolLocal, partido::setGolLocal, fixture.golLocal());
        huboCambios |= existenCambios(partido::getGolVisitante, partido::setGolVisitante, fixture.golVisitante());
        huboCambios |= existenCambios(
                partido.getEquipoLocal()::getLogo,
                partido.getEquipoLocal()::setLogo,
                fixture.local().logo()
        );
        huboCambios |= existenCambios(
                partido.getEquipoVisitante()::getLogo,
                partido.getEquipoVisitante()::setLogo,
                fixture.visitante().logo()
        );
        return huboCambios;
    }

    private <T> boolean existenCambios(Supplier<T> getterValor, Consumer<T> setter, T nuevovalor) {
        if (!Objects.equals(getterValor.get(), nuevovalor)) {
            setter.accept(nuevovalor);
            return true;
        }
        return false;
    }





    public List<Partido> listarTodosLosPartidos() {
        return partidoRepository.findAllPartidos();
    }

    @Override
    public Optional<Partido> encontrarPartidoPorFixtureId(Long id) {
        return partidoRepository.findByFixtureId(id);
    }
}

