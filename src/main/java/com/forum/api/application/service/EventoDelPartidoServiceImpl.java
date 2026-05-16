package com.forum.api.application.service;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.EventoDelPartidoService;
import com.forum.api.application.in.JugadorService;
import com.forum.api.application.out.EventoDelPartidoRepository;
import com.forum.api.domain.exception.MatchEventNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventoDelPartidoServiceImpl implements EventoDelPartidoService {

    private final EventoDelPartidoRepository repository;
    private final DataApiProvider eventoProvider;
    private final EventoMapper eventoMapper;
    private final JugadorService jugadorService;
    private final Map<Long, List<EventoDelPartido>> colaEventosPartido = new HashMap<>();

    public EventoDelPartidoServiceImpl(EventoDelPartidoRepository repository,
                                       DataApiProvider eventoProvider,
                                       EventoMapper eventoMapper,
                                       JugadorService jugadorService) {
        this.repository = repository;
        this.eventoProvider = eventoProvider;
        this.eventoMapper = eventoMapper;
        this.jugadorService = jugadorService;
    }
    @Override
    public EventoDelPartido agregarNuevoEventoDelPartido(EventoDelPartido eventoDelPartido) {
        try {
            return this.repository.saveEventoDelPartido(eventoDelPartido);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void borrarEventoDelPartido(Long id) {
        this.repository.deleteEventoDelPartido(id);
    }
    @Override
    public List<EventoDelPartido> obtenerEventosDelProvider(Partido partido) {
        return eventoProvider.proveerEventosPartido(partido.getFixtureId())
                .stream()
                .map(eventoDataDto -> eventoMapper.toNewDomain(
                        resolverEquipoEvento(partido, eventoDataDto.teamEvent().id()),
                        resolverJugadorEvento(eventoDataDto.playerEvent().id()),
                        eventoDataDto,
                        partido.getStatus(),
                        partido
                )).toList();
    }

    @Override
    public void agregarEventosPorFase(List<EventoDelPartido> eventoDelPartido) {
        eventoRepository.saveEventosPorFase(eventoDelPartido);
    }

    @Override
    public List<EventoDelPartido> listarEventosDelPartidoAPI(Partido partido) {
        return List.of();
    }

    private Equipo resolverEquipoEvento(Partido partido, Long idEvent) {
        Equipo local = partido.getEquipoLocal();
        Equipo visit = partido.getEquipoVisitante();

        if (local.getEquipoFixtureId().equals(idEvent)) {
            return local;
        } else if (visit.getEquipoFixtureId().equals(idEvent)) {
            return visit;
        }

        throw new IllegalArgumentException("El equipo no forma parte del partido");
    }

    private Jugador resolverJugadorEvento(Long jugadorFixtureId) {
        if (jugadorFixtureId == null) {
            throw new IllegalArgumentException("El evento no trae id de jugador");
        }
        return jugadorService
                .encontrarJugadorPorFixtureId(jugadorFixtureId)
                .orElseThrow(() -> new IllegalArgumentException("No existe jugador con fixture id: " + jugadorFixtureId));
    }
    @Override
    public EventoDelPartido encontrarEventoDelPartido(Long id) {
        return repository
                .findEventoDelPartidoById(id)
                .orElseThrow(
                        () -> new MatchEventNotFoundException("No se encuentra el match event"));
    }
    @Override
    public List<EventoDelPartido> listarEventosDelPartidoDB() {
        return repository.findAllEventos();
    }
}

