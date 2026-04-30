package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;

public interface EstadoPartido {
     void ejecutar(Partido partido);

     Integer calcularMinutoActual(Partido partido);
}

