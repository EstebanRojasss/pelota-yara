package com.forum.api.domain.model;

import com.forum.api.domain.exception.CompetenciaNotFoundException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Competencias {
    APERTURA("apertura"),
    CLAUSURA("clausura"),
    INTEGRADO("integrado"),
    COPA_PY("copa_py");


    private final String valor;

    private final static Map<String, Competencias> NOMBRES = Arrays
            .stream(Competencias.values())
            .collect(Collectors.toMap(Competencias::getValor, c -> c));

    Competencias(String valor) {
        this.valor = valor;
    }

    private static Optional<Competencias> from(String nombre){
        return Optional.ofNullable(NOMBRES.get(nombre));
    }

    public static Competencias fromValor(String nombreCompetencia){

        String normalizacion = nombreCompetencia
                .toLowerCase()
                .replace("-", "_");

        return from(normalizacion).orElseThrow(
                () -> new CompetenciaNotFoundException("Competencia inválida ->> " + nombreCompetencia));
    }

    public String getValor() {
        return valor;
    }

    public boolean comprobarCompetencia(String nombre){
        return NOMBRES.containsKey(nombre);
    }
}
