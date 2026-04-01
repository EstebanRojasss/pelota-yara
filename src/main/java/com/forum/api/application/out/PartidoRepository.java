package com.forum.api.application.out;

import com.forum.api.domain.model.Partido;

import java.util.Optional;

public interface PartidoRepository {
    Partido savePartido(Partido partido);
    Optional <Partido>findPartidoById(Long id);
    void deletePartido(Long id);


}
