package com.forum.api.domain.model;

import com.forum.api.domain.estado.EstadoPartido;
import com.forum.api.domain.estado.PrimerTiempo;
import com.forum.api.domain.exception.EquipoNotFoundException;

import java.util.List;
import java.util.Objects;

public class Partido {
    private final Long id;
    private StatusPartido status;
    private EstadoPartido estadoPartido;
    private Long fixtureId;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Integer golVisitante;
    private Integer golLocal;
    private Integer minutoActual;
    private Integer minutoAdicional1T;
    private Integer minutoAdicional2T;

    private Partido(Long id, StatusPartido status, Equipo equipoLocal, Equipo equipoVisitante, Integer golVisitante, Integer golLocal, Integer minutoActual, Integer minutoAdicional1T, Integer minutoAdicional2T, Long fixtureId) {
        this.id = id;
        this.status = status;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golVisitante = golVisitante;
        this.golLocal = golLocal;
        this.minutoActual = minutoActual;
        this.minutoAdicional1T = minutoAdicional1T;
        this.minutoAdicional2T = minutoAdicional2T;
        this.fixtureId = fixtureId;
        this.estadoPartido = new Definiendo();
        this.timeStampBase = Instant.now();
    }

    public void actualizar(StatusPartido status, Equipo equipoLocal, Equipo equipoVisitante, Integer golVisitante, Integer golLocal, Integer minutoActual, Integer minutoAdicional1T, Integer minutoAdicional2T) {
        this.status = status;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golVisitante = golVisitante;
        this.golLocal = golLocal;
        this.minutoActual = minutoActual;
        this.minutoAdicional1T = minutoAdicional1T;
        this.minutoAdicional2T = minutoAdicional2T;
    }

    public static Partido restore(Long id, StatusPartido statusPartido, Equipo equipoLocal, Equipo equipoVisitante, Integer golLocal, Integer golVisitante, Integer minutoActual, Integer minutoAdicional1T, Integer minutoAdicional2T, Long fixtureId) {
        return new Partido(id,
                statusPartido,
                equipoLocal,
                equipoVisitante,
                golVisitante,
                golLocal,
                minutoActual,
                minutoAdicional1T,
                minutoAdicional2T,
                fixtureId);
    }

    public static Partido createFromApi(Equipo equipoLocal, Equipo equipoVisitante, Integer golLocal, Integer golVisitante, Integer minutoActual, StatusPartido status, Long fixtureId) {
        return new Partido(null,
                status,
                equipoLocal,
                equipoVisitante,
                golVisitante,
                golLocal,
                minutoActual,
                0,
                0,
                fixtureId);
    }

    public static Partido createFromLocal(Equipo equipoLocal, Equipo equipoVisitante){
        return new Partido(null,
                StatusPartido.NO_INICIADO,
                equipoLocal,
                equipoVisitante,
                0,
                0,
                0,
                0,
                0,
                0L);
    }
    public void fijarBaseMinuto(Integer minutoBase) {
        this.minutoBase = minutoBase;
        this.timeStampBase = Instant.now();
        this.minutoActual = minutoBase;
    }

    public Integer calcularMinutoActual() {
        long segundos = Duration.between(timeStampBase, Instant.now()).getSeconds();
        return minutoBase + (int)(segundos / 60);
    }

    public void actualizarMinutoActual() {
        if (timeStampBase != null) {
            this.minutoActual = calcularMinutoActual();
        }
    }

    public void aumentarMinuto() {
        this.minutoActual++;
    }



    public void ejecutar() {
        this.estadoPartido.ejecutar(this);
    }

    public void aumentarMarcador(Equipo equipo) {
        if (equipo.equals(this.equipoLocal))this.golLocal++;
        else if (equipo.equals(this.equipoVisitante)) this.golVisitante++;
        else throw new EquipoNotFoundException("El equipo no forma parte del partido");
    }

    public void restarMarcador(Equipo equipo){
        if(equipo.equals(this.equipoLocal))this.golLocal--;
        else if (equipo.equals(this.equipoVisitante)) this.golVisitante--;
        else throw new EquipoNotFoundException("El equipo no forma parte del partido");
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

    private void setMinutoActual(int minutoActual) {
        this.minutoActual = minutoActual;
    }

    public void cambiarEstado(EstadoPartido estado) {
        this.estadoPartido = estado;
    }

    public Long getId() {
        return this.id;
    }

    public StatusPartido getStatus() {
        return this.status;
    }

    public EstadoPartido getEstadoPartido() {
        return this.estadoPartido;
    }

    public Equipo getEquipoLocal() {
        return this.equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return this.equipoVisitante;
    }

    public Integer getGolVisitante() {
        return this.golVisitante;
    }

    public Integer getGolLocal() {
        return this.golLocal;
    }

    public Integer getMinutoActual() {
        return this.minutoActual;
    }

    public List<Equipo> equiposDelPartido() {
        return List.of(this.equipoLocal, this.equipoVisitante);
    }

    public void setStatus(StatusPartido status) {
        this.status = status;
    }

    public void setMinutoAdicional1T(Integer minutoAdicional1T) {
        this.minutoAdicional1T = minutoAdicional1T;
    }

    public void setMinutoAdicional2T(Integer minutoAdicional2T) {
        this.minutoAdicional2T = minutoAdicional2T;
    }

    public Integer getMinutoAdicional1T() {
        return this.minutoAdicional1T;
    }

    public Integer getMinutoAdicional2T() {
        return this.minutoAdicional2T;
    }

    public Long getFixtureId() {
        return fixtureId;
    }

    public Instant getTimeStampBase() {
        return timeStampBase;
    }

    public void setTimeStampBase(Instant timeStampBase) {
        this.timeStampBase = timeStampBase;
    }

    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Partido partido = (Partido) o;
        return Objects.equals(this.id, partido.id) && this.estadoPartido == partido.estadoPartido && Objects.equals(this.equipoLocal, partido.equipoLocal) && Objects.equals(this.equipoVisitante, partido.equipoVisitante) && Objects.equals(this.golVisitante, partido.golVisitante) && Objects.equals(this.golLocal, partido.golLocal) && Objects.equals(this.minutoActual, partido.minutoActual);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.estadoPartido, this.equipoLocal, this.equipoVisitante, this.golVisitante, this.golLocal, this.minutoActual);
    }

    public String toString() {
        return "Partido{id=" + this.id + ", status=" +this.status + ", estadoPartido=" +this.estadoPartido + ", equipoLocal=" + this.equipoLocal + ", equipoVisitante=" + this.equipoVisitante + ", golVisitante=" + this.golVisitante + ", golLocal=" + this.golLocal + ", minutoActual=" + this.minutoActual + ", minutoAdicional1T=" + this.minutoAdicional1T + ", minutoAdicional2T=" + this.minutoAdicional2T + "}";
    }
}

