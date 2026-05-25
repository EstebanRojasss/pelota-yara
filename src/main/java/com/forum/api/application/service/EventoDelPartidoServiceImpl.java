package com.forum.api.application.service;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.EventoDelPartidoService;
import com.forum.api.application.in.JugadorService;
import com.forum.api.application.in.dto.evento.PlayerEventDataDto;
import com.forum.api.application.out.EventoDelPartidoRepository;
import com.forum.api.domain.exception.MatchEventNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EventoDelPartidoServiceImpl implements EventoDelPartidoService {

    private final EventoDelPartidoRepository eventoRepository;
    private final DataApiProvider eventoProvider;
    private final EventoMapper eventoMapper;
    private final JugadorService jugadorService;
    private final PartidoContextService contextService;

    public EventoDelPartidoServiceImpl(EventoDelPartidoRepository eventoRepository,
                                       DataApiProvider eventoProvider,
                                       EventoMapper eventoMapper,
                                       JugadorService jugadorService, PartidoContextService contextService) {
        this.eventoRepository = eventoRepository;
        this.eventoProvider = eventoProvider;
        this.eventoMapper = eventoMapper;
        this.jugadorService = jugadorService;
        this.contextService = contextService;
    }
    @Override
    public EventoDelPartido agregarEvento(EventoDelPartido eventoDelPartido) {
        try {
            return this.eventoRepository.saveEventoDelPartido(eventoDelPartido);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void borrarEventoDelPartido(Long id) {
        this.eventoRepository.deleteEventoDelPartido(id);
    }
    @Override
    public List<EventoDelPartido> obtenerEventosDelProvider(Partido partido) {
        return eventoProvider.proveerEventosPartido(partido.getFixtureId())
                .stream()
                .map(eventoDataDto -> eventoMapper.toNewDomain(
                        resolverEquipoEvento(partido, eventoDataDto.teamEvent().id()),
                        resolverJugadorEvento(
                                eventoDataDto.playerEvent(),
                                resolverEquipoEvento(partido, eventoDataDto.teamEvent().id()),
                                partido),
                        eventoDataDto,
                        partido.getStatus(),
                        partido
                )).toList();
    }

    @Override
    public void agregarEventosPorFase(List<EventoDelPartido> eventoDelPartido) {
        eventoRepository.saveEventosPorFase(eventoDelPartido);
    }

    private Jugador resolverJugadorEvento(PlayerEventDataDto playerEvent, Equipo equipo, Partido partido) {
        if (playerEvent == null || playerEvent.id() == null) {
            return null;
        }

        PartidoRuntimeContext contexto = contextService.obtenerContexto(partido.getFixtureId());

        Jugador jugador = contexto.consultarJugador(playerEvent.id());

        if(jugador == null){
            jugador = jugadorService.retornarOGuardarSiNoExiste(playerEvent,equipo);
            contexto.consultarYAgregarJugadorSiNoExiste(jugador);
        }

        return jugador;
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

    @Override
    public EventoDelPartido encontrarEventoDelPartido(Long id) {
        return eventoRepository
                .findEventoDelPartidoById(id)
                .orElseThrow(
                        () -> new MatchEventNotFoundException("No se encuentra el match event"));
    }
    @Override
    public List<EventoDelPartido> listarEventosDelPartidoDB() {
        return eventoRepository.findAllEventos();
    }
    @Override
    public List<EventoDelPartido> listarEventosDelPartidoAPI(Partido partido) {
        return List.of();
    }
}

