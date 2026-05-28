package com.forum.api.domain.estado;

import com.forum.api.domain.model.partido.Partido;
import com.forum.api.domain.model.partido.StatusPartido;

public interface EstadoPartido {
     void ejecutar(Partido partido);

     Integer calcularMinutoActual(Partido partido);

     EstadoPartido siguienteEstado(Partido partido);

     String nombreEstado();

     StatusPartido obtenerFaseFinalizada();

     default void onEnter(Partido partido){}

     default void onExit(Partido partido){}


}

