package com.forum.api.domain.model;

import com.forum.api.domain.exception.EquipoNotFoundException;

import java.util.List;
import java.util.Objects;

public class Partido {

    private final Long id;
    private StatusPartido status;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Integer golVisitante;
    private Integer golLocal;
    private Integer minutoActual;
    private Integer minutoAdicional1T;
    private Integer minutoAdicional2T;


    public Partido(Long id,
                   StatusPartido status,
                   Equipo equipoLocal,
                   Equipo equipoVisitante,
                   Integer golVisitante,
                   Integer golLocal,
                   Integer minutoActual,
                   Integer minutoAdicional1T,
                   Integer minutoAdicional2T) {
        this.id = id;
        this.status = status;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golVisitante = golVisitante;
        this.golLocal = golLocal;
        this.minutoActual = minutoActual;
        this.minutoAdicional1T = minutoAdicional1T;
        this.minutoAdicional2T = minutoAdicional2T;
    }

    public static Partido restore(Long id,
                                  StatusPartido status,
                                  Equipo equipoLocal,
                                  Equipo equipoVisitante,
                                  Integer golLocal,
                                  Integer golVisitante,
                                  Integer minutoActual,
                                  Integer minutoAdicional1T,
                                  Integer minutoAdicional2T) {
        return new Partido(id,
                status,
                equipoLocal,
                equipoVisitante,
                golVisitante,
                golLocal,
                minutoActual,
                minutoAdicional1T,
                minutoAdicional2T);
    }

    public static Partido create(Equipo equipoLocal,
                                 Equipo equipoVisitante,
                                 Integer golLocal,
                                 Integer golVisitante) {
        return new Partido(null,
                StatusPartido.EN_JUEGO,
                equipoLocal,
                equipoVisitante,
                golVisitante,
                golLocal,
                0,
                0,
                0);
    }

    public void aumentarMinuto() {
        this.minutoActual++;
    }

    public void aplicarEvento(MatchEvent matchEvent) {
        switch (matchEvent.getEventoPartido()) {
            case GOL -> aumentarMarcador(matchEvent);
            case DESCANSO -> this.status = StatusPartido.MEDIO_TIEMPO;
            case FIN_PARTIDO -> this.status = StatusPartido.FINALIZADO;
        }
    }

    private void aumentarMarcador(MatchEvent matchEvent) {
        if (matchEvent.getEquipo().equals(equipoLocal)) golLocal++;
        else if (matchEvent.getEquipo().equals(equipoVisitante)) golVisitante++;
        else throw new EquipoNotFoundException("El equipo no forma parte del partido");
    }

    public Long getId() {
        return id;
    }

    public StatusPartido getStatus() {
        return status;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public Integer getGolVisitante() {
        return golVisitante;
    }

    public Integer getGolLocal() {
        return golLocal;
    }

    public Integer getMinutoActual() {
        return minutoActual;
    }

    public List<Equipo> equiposDelPartido() {
        return List.of(equipoLocal, equipoVisitante);
    }

    public void setStatus(StatusPartido status) {
        this.status = status;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public void setGolVisitante(Integer golVisitante) {
        this.golVisitante = golVisitante;
    }

    public void setGolLocal(Integer golLocal) {
        this.golLocal = golLocal;
    }

    public void setMinutoActual(Integer minutoActual) {
        this.minutoActual = minutoActual;
    }

    public Integer getMinutoAdicional1T() {
        return minutoAdicional1T;
    }

    public void setMinutoAdicional1T(Integer minutoAdicional1T) {
        this.minutoAdicional1T = minutoAdicional1T;
    }

    public Integer getMinutoAdicional2T() {
        return minutoAdicional2T;
    }

    public void setMinutoAdicional2T(Integer minutoAdicional2T) {
        this.minutoAdicional2T = minutoAdicional2T;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return Objects.equals(id, partido.id) && status == partido.status && Objects.equals(equipoLocal, partido.equipoLocal) && Objects.equals(equipoVisitante, partido.equipoVisitante) && Objects.equals(golVisitante, partido.golVisitante) && Objects.equals(golLocal, partido.golLocal) && Objects.equals(minutoActual, partido.minutoActual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, equipoLocal, equipoVisitante, golVisitante, golLocal, minutoActual);
    }
}
