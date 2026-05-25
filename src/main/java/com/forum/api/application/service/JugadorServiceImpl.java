package com.forum.api.application.service;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.EquipoService;
import com.forum.api.application.in.JugadorService;
import com.forum.api.application.in.dto.JugadorDataDto;
import com.forum.api.application.in.dto.evento.PlayerEventDataDto;
import com.forum.api.application.out.JugadorRepository;
import com.forum.api.domain.exception.JugadorNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JugadorServiceImpl implements JugadorService {
    private final JugadorRepository jugadorRepository;
    private final Map<Long, Jugador> cacheJugadoresPorFixtureId = new ConcurrentHashMap<>();
    private final DataApiProvider jugadorProvider;
    private final EquipoService equipoService;
    private final JugadorMapper jugadorMapper;

    public JugadorServiceImpl(JugadorRepository jugadorRepository, DataApiProvider jugadorProvider, EquipoService equipoService, JugadorMapper jugadorMapper) {
        this.jugadorRepository = jugadorRepository;
        this.jugadorProvider = jugadorProvider;
        this.equipoService = equipoService;
        this.jugadorMapper = jugadorMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Jugador agregarNuevoJugador(Jugador jugador) {
        try {
            return jugadorRepository.guardarJugador(jugador);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ocurrió un error al intentar agregar nuevo jugador", e);
        }
    }

    @Override
    public List<Jugador> listarJugadoresPorEquipo(Long equipoId) {
        return jugadorRepository.listarJugadoresPorEquipo(equipoId);
    }

    @Override
    public List<Jugador> listarJugadoresDesdeApi(Long id) {
        return jugadorProvider.
                proveerJugadoresDeUnEquipo(id).
                stream().
                map(this::guardarOActualizarJugador)
                .toList();
    }

    @Transactional
    public Jugador guardarOActualizarJugador(JugadorDataDto jugadorData) {
        Equipo equipoJugador = equipoService.resolverExistenciaEquipo(jugadorData.equipoDto());
        Jugador jugador = cacheJugadoresPorFixtureId.get(jugadorData.id());
        if (jugador == null) {
            jugador = jugadorRepository
                    .encontrarJugadorPorFixtureId(jugadorData.id())
                    .orElseGet(() -> jugadorRepository.guardarJugador(
                            jugadorMapper.toNewDomain(jugadorData, equipoJugador)
                    ));
            cacheJugadoresPorFixtureId.put(jugadorData.id(), jugador);
        }
        if (!jugador.getEquipo().getEquipoFixtureId().equals(jugadorData.equipoDto().id())) {
            jugadorMapper.actualizarSiHayCambios(jugadorData, jugador, equipoJugador);
            cacheJugadoresPorFixtureId.put(jugadorData.id(), jugador);
        }

        return jugador;
    }

    @Override
    public Jugador retornarOGuardarSiNoExiste(PlayerEventDataDto jugadorDto, Equipo equipo) {
            Optional<Jugador> comprobarJugador = encontrarJugadorPorFixtureId(jugadorDto.id());

            if(comprobarJugador.isPresent()){
                return comprobarJugador.get();
            }

        try {
            return agregarNuevoJugador(
                    Jugador.create(
                            jugadorDto.name(),
                            null,
                            jugadorDto.id(),
                            equipo)
            );

        } catch (DataIntegrityViolationException e) {
            return encontrarJugadorPorFixtureId(jugadorDto.id())
                    .orElseThrow(() -> new RuntimeException("No se pudo guardar ni recuperar el jugador"));
        }
    }


    @Override
    public Optional<Jugador> encontrarJugadorPorFixtureId(Long fixtureId) {
        Jugador jugadorEnCache = cacheJugadoresPorFixtureId.get(fixtureId);
        if (jugadorEnCache != null) {
            return Optional.of(jugadorEnCache);
        }
        Optional<Jugador> jugador = jugadorRepository.encontrarJugadorPorFixtureId(fixtureId);
        jugador.ifPresent(value -> cacheJugadoresPorFixtureId.put(fixtureId, value));
        return jugador;
    }

    @Override
    public Jugador encontrarJugadorPorId(Long id) {
        return jugadorRepository.encontrarJugador(id).orElseThrow(() -> new JugadorNotFoundException("Jugador no encontrado."));
    }

    @Override
    public void eliminarJugadorPorId(Long id) {
        if (jugadorRepository.encontrarJugador(id).isEmpty()) {
            throw new JugadorNotFoundException("Jugador no encontrado");
        }
        jugadorRepository.borrarJugador(id);
    }
}
