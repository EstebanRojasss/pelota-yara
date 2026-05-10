package com.forum.api.application.in;

import com.forum.api.domain.model.Jugador;

import java.util.List;

public interface JugadorService {
     Jugador agregarNuevoJugador(Jugador var1);

     List<Jugador> listarJugadoresEquipoDB(Long var1);

     Jugador encontrarJugadorPorId(Long var1);

     void eliminarJugadorPorId(Long var1);

}

