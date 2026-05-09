package com.forum.api.application.service;

import com.forum.api.application.in.EventoDelPartidoService;
import com.forum.api.application.out.EventoDelPartidoRepository;
import com.forum.api.domain.exception.MatchEventNotFoundException;
import com.forum.api.domain.model.evento.EventoDelPartido;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventoDelPartidoServiceImpl implements EventoDelPartidoService {
    private final EventoDelPartidoRepository repository;

    public EventoDelPartidoServiceImpl(EventoDelPartidoRepository repository) {
        this.repository = repository;
    }

    public EventoDelPartido agregarNuevoEventoDelPartido(EventoDelPartido eventoDelPartido) {
        try {
            return this.repository.saveEventoDelPartido(eventoDelPartido);
        }
        catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    public void borrarEventoDelPartido(Long id) {
        this.repository.deleteEventoDelPartido(id);
    }

    public Set<EventoDelPartido> listarEventosDelPartido() {
        return Set.of();
    }

    public EventoDelPartido encontrarEventosDelPartido(Long id) {
        return repository
                .findMatchEventById(id)
                .orElseThrow(
                        () -> new MatchEventNotFoundException("No se encuentra el match event"));
    }
}

