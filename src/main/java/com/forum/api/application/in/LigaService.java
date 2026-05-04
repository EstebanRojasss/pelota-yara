package com.forum.api.application.in;

import com.forum.api.domain.model.Liga;

import java.util.Map;

public interface LigaService {
    Liga agregarNuevaLiga(Liga liga);
    Map<Long, Liga> ligaCache();
}
