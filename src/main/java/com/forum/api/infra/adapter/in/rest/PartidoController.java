package com.forum.api.infra.adapter.in.rest;

import com.forum.api.application.in.PartidoService;
import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.domain.model.Partido;
import com.forum.api.infra.adapter.in.rest.dto.PartidoRequestDto;
import com.forum.api.infra.adapter.in.rest.dto.PartidoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PartidoController {
    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }


    @GetMapping("/partidos")
    public ResponseEntity<List<PartidoResponseDto>> listarTodosLosPartidos() {
        return ResponseEntity.ok().body(partidoService.listarTodosLosPartidos()
                .stream()
                .map(PartidoResponseDto::fromDomainExistent)
                .collect(Collectors.toList()));
    }

    @GetMapping("/partidos/envivo")
    public ResponseEntity<List<PartidoResponseDto>> listarTodosLosPartidosEnVivo() {
        return ResponseEntity.ok().body(partidoService.encontrarTodosLosPartidosEnVivo()
                .stream()
                .map(PartidoResponseDto::fromDomainExistent)
                .collect(Collectors.toList()));
    }
    @PostMapping("/partidos")
    public ResponseEntity<PartidoResponseDto> agregarNuevoPartido(@RequestBody PartidoRequestDto partidoRequest) {

        CrearPartidoCommand command = new CrearPartidoCommand(
                partidoRequest.idEquipoLocal(),
                partidoRequest.idEquipoVisitante()
        );

        Partido response = partidoService.guardarNuevoPartido(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(PartidoResponseDto.fromDomainExistent(response));
    }
}

