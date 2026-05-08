package com.forum.api.application.service;

import com.forum.api.application.in.MatchEventService;
import com.forum.api.application.out.MatchEventRepository;
import com.forum.api.domain.exception.MatchEventNotFoundException;
import com.forum.api.domain.model.evento.EventoDelPartido;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MatchEventServiceImpl implements MatchEventService {
    private final MatchEventRepository repository;

    public MatchEventServiceImpl(MatchEventRepository repository) {
        this.repository = repository;
    }

    public EventoDelPartido agregarNuevoMatchEvent(EventoDelPartido eventoDelPartido) {
        try {
            return this.repository.saveMatchEvent(eventoDelPartido);
        }
        catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    public void borrarMatchEvent(Long id) {
        this.repository.deleteMatchEvent(id);
    }

    public Set<EventoDelPartido> listarMatchEvents() {
        return Set.of();
    }

    public EventoDelPartido encotrarMatchEvent(Long id) {
        return repository
                .findMatchEventById(id)
                .orElseThrow(
                        () -> new MatchEventNotFoundException("No se encuentra el match event"));
    }
}

