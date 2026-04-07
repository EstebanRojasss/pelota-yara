package com.forum.api.application.service;

import com.forum.api.application.in.JugadorService;
import com.forum.api.application.out.JugadorRepository;
import com.forum.api.domain.exception.JugadorNotFoundException;
import com.forum.api.domain.model.Jugador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorServiceImpl
implements JugadorService {
    private final JugadorRepository repository;

    public JugadorServiceImpl(JugadorRepository repository) {
        this.repository = repository;
    }

    public Jugador agregarNuevoJugador(Jugador jugador) {
        try {
            return this.repository.guardarJugador(jugador);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Ocurrio un error al intentar agregar nuevo jugador");
        }
    }

    public List<Jugador> listarJugadoresEquipo(Long equipoId) {
        return this.repository.listarJugadoresPorEquipo(equipoId);
    }

    public Jugador encontrarJugadorPorId(Long id) {
        return (Jugador)this.repository.encontrarJugador(id).orElseThrow(() -> new JugadorNotFoundException("Jugador no encontrado."));
    }

    public void eliminarJugadorPorId(Long id) {
        if (this.repository.encontrarJugador(id).isEmpty()) {
            throw new JugadorNotFoundException("Jugador no encontrado");
        }
        this.repository.borrarJugador(id);
    }

    public Jugador actualizarDatosJugador(Jugador jugador) {
        return null;
    }
}

