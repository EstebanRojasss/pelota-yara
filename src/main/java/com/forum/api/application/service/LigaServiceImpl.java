package com.forum.api.application.service;

import com.forum.api.application.in.LigaService;
import com.forum.api.application.out.LigaRepository;
import com.forum.api.domain.model.Liga;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class LigaServiceImpl implements LigaService {

    private final LigaRepository ligaRepository;
    private final Map<Long, Liga> ligaCache = new HashMap<>();

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

    public Map<Long, Liga> ligaCache(){
        return ligaCache;
    }
}
