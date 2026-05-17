package com.forum.api.domain.model.evento;

import com.forum.api.domain.model.partido.StatusPartido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class StoreEvent {
    private static final Logger log = LoggerFactory.getLogger(StoreEvent.class);
    private final Map<StatusPartido, List<EventoDelPartido>> eventosPorFase = new LinkedHashMap<>();
    private StatusPartido faseActual;
    private final Set<EventKey> eventosProcesados = new HashSet<>();

    public StoreEvent() {
    }

    public void inicializar(StatusPartido statusPartido) {
        eventosPorFase.put(statusPartido, new ArrayList<>());
        faseActual = statusPartido;
    }


    public List<EventoDelPartido> obtenerEventosPorFase(StatusPartido fase) {
        return eventosPorFase.getOrDefault(fase, new ArrayList<>());
    }

    public void agregarEvento(List<EventoDelPartido> eventos) {
        if (!eventos.isEmpty() && faseActual != null) {
            for (EventoDelPartido evento : eventos) {
                if (eventosProcesados.contains(EventKey.from(evento))) {
                    return;
                }

                    eventosProcesados.add(EventKey.from(evento));
                    eventosPorFase.get(this.faseActual).add(evento);

            }
            log.info("Fase {}: [{}]", this.faseActual, eventosPorFase.get(faseActual));
        }
    }


    public Map<StatusPartido, List<EventoDelPartido>> obtenerTodosEventos() {
        return new LinkedHashMap<>(eventosPorFase);
    }

    public void cambiarFase(StatusPartido nuevaFase) {
        log.info("Cambiando fase {} -> {}", faseActual, nuevaFase);
        faseActual = nuevaFase;
    }

}
