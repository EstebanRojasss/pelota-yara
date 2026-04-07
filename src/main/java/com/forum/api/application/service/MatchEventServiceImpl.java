package com.forum.api.application.service;

import com.forum.api.application.in.MatchEventService;
import com.forum.api.application.out.MatchEventRepository;
import com.forum.api.domain.exception.MatchEventNotFoundException;
import com.forum.api.domain.model.MatchEvent;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MatchEventServiceImpl
implements MatchEventService {
    private final MatchEventRepository repository;

    public MatchEventServiceImpl(MatchEventRepository repository) {
        this.repository = repository;
    }

    public MatchEvent agregarNuevoMatchEvent(MatchEvent matchEvent) {
        try {
            return this.repository.saveMatchEvent(matchEvent);
        }
        catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    public void borrarMatchEvent(Long id) {
        this.repository.deleteMatchEvent(id);
    }

    public Set<MatchEvent> listarMatchEvents() {
        return Set.of();
    }

    public MatchEvent encotrarMatchEvent(Long id) {
        return (MatchEvent)this.repository.findMatchEventById(id).orElseThrow(() -> new MatchEventNotFoundException("No se encontr\u00f3 el match event"));
    }
}

