package com.forum.api.application.out;

import com.forum.api.domain.model.MatchEvent;

import java.util.Optional;

public interface MatchEventRepository {
     MatchEvent saveMatchEvent(MatchEvent var1);

     void deleteMatchEvent(Long var1);

     Optional<MatchEvent> findMatchEventById(Long var1);
}

