package com.forum.api.infra.adapter.in.rest;

import com.forum.api.application.in.JugadorService;
import com.forum.api.domain.model.Jugador;
import com.forum.api.infra.adapter.in.rest.dto.JugadorRequestDto;
import com.forum.api.infra.adapter.in.rest.dto.JugadorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class JugadorController {
    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping("/jugadores")
    public ResponseEntity<JugadorResponseDto> agregarNuevoJugador(@RequestBody JugadorRequestDto jugadorDto) {

        JugadorResponseDto response = JugadorResponseDto
                .fromDomain(jugadorService.agregarNuevoJugador(jugadorDto.toDomain()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }
}

