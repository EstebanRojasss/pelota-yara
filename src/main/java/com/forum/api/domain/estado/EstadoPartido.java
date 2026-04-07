package com.forum.api.domain.estado;

import com.forum.api.domain.model.Partido;

public interface EstadoPartido {
     void ejecutar(Partido var1);

     void setPartido(Partido var1);

     EstadoPartido getTipoEstadoPartido();
}

