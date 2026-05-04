package com.forum.api.application.service;

import com.forum.api.application.in.LigaService;
import com.forum.api.application.out.LigaRepository;
import com.forum.api.domain.model.Liga;

public class LigaServiceImpl implements LigaService {

    private final LigaRepository ligaRepository;

    public LigaServiceImpl(LigaRepository ligaRepository) {
        this.ligaRepository = ligaRepository;
    }

    @Override
    public Liga agregarNuevaLiga(Liga liga) {
        try {
            return ligaRepository.save(liga);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ocurrió un error en la persistencia de nueva liga");
        }
    }
}
