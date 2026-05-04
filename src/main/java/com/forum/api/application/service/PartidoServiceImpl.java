package com.forum.api.application.service;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.EquipoService;
import com.forum.api.application.in.PartidoService;
import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.application.in.dto.FixtureData;
import com.forum.api.application.in.dto.TeamDataDto;
import com.forum.api.application.out.PartidoRepository;
import com.forum.api.domain.exception.PartidoNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Partido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class PartidoServiceImpl implements PartidoService {
    private static final Logger log = LoggerFactory.getLogger(PartidoServiceImpl.class);
    private final PartidoRepository partidoRepository;
    private final EquipoService equipoService;
    private final DataApiProvider fixtureProvider;
    private final PartidoMapper partidoMapper;
    private final Map<Long, Partido> partidoPorFixtureIdCache = new HashMap<>();



    public PartidoServiceImpl(PartidoRepository partidoRepository, EquipoService equipoService, DataApiProvider fixtureProvider, PartidoMapper partidoMapper) {
        this.partidoRepository = partidoRepository;
        this.equipoService = equipoService;
        this.fixtureProvider = fixtureProvider;
        this.partidoMapper = partidoMapper;
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
        return partidoPorFixtureIdCache.values().stream().toList();
    }

    @Transactional
    public List<Partido> encontrarTodosLosPartidosEnVivo() {
        return fixtureProvider.
                proveerDatosFixture().
                stream().
                map(this::procesarDatosFixture).
                toList();
    }

    private Partido procesarDatosFixture(FixtureData fixture) {
        Equipo local = resolverEquipo(fixture.local());
        Equipo visitante = resolverEquipo(fixture.visitante());
        Partido partido = partidoPorFixtureIdCache.get(fixture.id());

        if (partido == null) {

            partido = partidoRepository.savePartido(
                    partidoMapper.toNewDomain(fixture, local, visitante)
            );
            partidoPorFixtureIdCache.put(fixture.id(), partido);

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


    private Equipo resolverEquipo(TeamDataDto team) {
        Equipo equipo = equipoService.cacheEquipos().get(team.id());
        if (equipo == null) {
            equipo = equipoService.agregarNuevoEquipo(
                    Equipo.create(team.nombre(), null, null, team.id(), team.logo())
            );
            equipoService.cacheEquipos().put(team.id(), equipo);
        }

        return equipo;
    }

    private Liga resolverLiga(LigaDataDto ligaDto, Partido partido){
        Liga liga = partidoPorFixtureIdCache.get(partido.getId()).getLiga();
        if(liga == null){
            liga = ligaService.agregarNuevaLiga(
                    Liga.create(ligaDto.nombre(),
                            ligaDto.pais(),
                            ligaDto.id(),
                            ligaDto.temporada())
            );
            ligaService.ligaCache().put(ligaDto.id(), liga);
        }
        return liga;
    }

    public List<Partido> listarTodosLosPartidos() {
        return partidoRepository.findAllPartidos();
    }

    @Override
    public Optional<Partido> encontrarPartidoPorFixtureId(Long id) {
        return partidoRepository.findByFixtureId(id);
    }
}

