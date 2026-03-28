package com.forum.api.application.out;

import com.forum.api.domain.MatchEvent;

import java.util.Optional;

public interface MatchEventRepository {
    MatchEvent saveMatchEvent(MatchEvent matchEvent);
    void deleteMatchEvent(Long id);
    Optional<MatchEvent> findMatchEventById(Long id);
}
