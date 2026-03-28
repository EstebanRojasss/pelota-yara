package com.forum.api.application.service;

import com.forum.api.application.in.JugadorService;
import com.forum.api.application.out.JugadorRepository;
import com.forum.api.domain.Jugador;
import com.forum.api.domain.exception.JugadorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository repository;

    public JugadorServiceImpl(JugadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Jugador agregarNuevoJugador(Jugador jugador) {
        try {
            return repository.guardarJugador(jugador);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ocurrio un error al intentar agregar nuevo jugador");
        }
    }

    @Override
    public List<Jugador> listarJugadoresEquipo(Long equipoId) {
        return repository.listarJugadoresPorEquipo(equipoId);
    }

    @Override
    public Jugador encontrarJugadorPorId(Long id) {
        return repository.encontrarJugador(id)
                .orElseThrow(() -> new JugadorNotFoundException("Jugador no encontrado."));
    }

    @Override
    public void eliminarJugadorPorId(Long id) {
        if (repository.encontrarJugador(id).isEmpty()) {
            throw new JugadorNotFoundException("Jugador no encontrado");
        }
        repository.borrarJugador(id);
    }

    @Override
    public Jugador actualizarDatosJugador(Jugador jugador) {
        return null;
    }
}
