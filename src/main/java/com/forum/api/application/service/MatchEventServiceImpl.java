package com.forum.api.application.service;

import com.forum.api.application.in.MatchEventService;
import com.forum.api.domain.model.MatchEvent;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class MatchEventServiceImpl implements MatchEventService {
    @Override
    public MatchEvent agregarNuevoMatchEvent(MatchEvent matchEvent) {
        return null;
    }

    @Override
    public void borrarMatchEvent(Long id) {

    }

    @Override
    public Set<MatchEvent> listarMatchEvents() {
        return Set.of();
    }

    @Override
    public MatchEvent encotrarMatchEvent(Long id) {
        return null;
    }
}
