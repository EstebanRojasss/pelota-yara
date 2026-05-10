package com.forum.api.application.service;

import com.forum.api.application.in.DataApiProvider;
import com.forum.api.application.in.EventoDelPartidoService;
import com.forum.api.application.out.EventoDelPartidoRepository;
import com.forum.api.domain.exception.MatchEventNotFoundException;
import com.forum.api.domain.model.Equipo;
import com.forum.api.domain.model.evento.EventoDelPartido;
import com.forum.api.domain.model.partido.Partido;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EventoDelPartidoServiceImpl implements EventoDelPartidoService {

    private final EventoDelPartidoRepository repository;
    private final DataApiProvider eventoProvider;
    private final EventoMapper eventoMapper;
    private final Map<Long, List<EventoDelPartido>> colaEventosPartido = new HashMap<>();

    public EventoDelPartidoServiceImpl(EventoDelPartidoRepository repository, DataApiProvider eventoProvider, EventoMapper eventoMapper) {
        this.repository = repository;
        this.eventoProvider = eventoProvider;
        this.eventoMapper = eventoMapper;
    }

    public EventoDelPartido agregarNuevoEventoDelPartido(EventoDelPartido eventoDelPartido) {
        try {
            return this.repository.saveEventoDelPartido(eventoDelPartido);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    public void borrarEventoDelPartido(Long id) {
        this.repository.deleteEventoDelPartido(id);
    }

    public List<EventoDelPartido> obtenerEventosDelProvider(Partido partido) {
        return eventoProvider.proveerEventosPartido(partido.getId())
                .stream()
                .map(eventoDataDto -> {
                    eventoMapper.toNewDomain(
                            resolverEquipoEvento(partido, eventoDataDto.teamEvent().id()),

                    )
                });
    }

    @Override
    public List<EventoDelPartido> listarEventosDelPartidoAPI() {
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

    public EventoDelPartido encontrarEventoDelPartido(Long id) {
        return repository
                .findEventoDelPartidoById(id)
                .orElseThrow(
                        () -> new MatchEventNotFoundException("No se encuentra el match event"));
    }

    public Set<EventoDelPartido> listarEventosDelPartidoDB() {
        return Set.of();
    }
}

