package com.forum.api.application.service;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.EventoDelPartidoService;
import com.forum.api.application.in.JugadorService;
import com.forum.api.application.in.command.CrearJugadorCommand;
import com.forum.api.application.in.dto.evento.PlayerEventDataDto;
import com.forum.api.application.out.EventoDelPartidoRepository;
import com.forum.api.domain.exception.MatchEventNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.Jugador;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventoDelPartidoServiceImpl implements EventoDelPartidoService {

    private final EventoDelPartidoRepository eventoRepository;
    private final DataApiProvider eventoProvider;
    private final EventoMapper eventoMapper;
    private final JugadorService jugadorService;

    public EventoDelPartidoServiceImpl(EventoDelPartidoRepository eventoRepository,
                                       DataApiProvider eventoProvider,
                                       EventoMapper eventoMapper,
                                       JugadorService jugadorService) {
        this.eventoRepository = eventoRepository;
        this.eventoProvider = eventoProvider;
        this.eventoMapper = eventoMapper;
        this.jugadorService = jugadorService;
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
                        resolverJugadorEvento(eventoDataDto.playerEvent()),
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

    private Jugador resolverJugadorEvento(PlayerEventDataDto playerEvent) {
        if (playerEvent.id() == null) {
            throw new IllegalArgumentException("El evento no trae id de jugador");
        }
        return jugadorService
                .guardarJugadorSiNoExiste(CrearJugadorCommand.from(playerEvent.id(), playerEvent.name()));
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
}

