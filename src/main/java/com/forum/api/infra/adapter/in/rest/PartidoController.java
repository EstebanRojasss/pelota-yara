package com.forum.api.infra.adapter.in.rest;

import com.forum.api.application.in.PartidoService;
import com.forum.api.application.in.SSeRegistrarUseCase;
import com.forum.api.application.in.command.CrearPartidoCommand;
import com.forum.api.domain.model.partido.Partido;
import com.forum.api.infra.adapter.in.rest.dto.PartidoRequestDto;
import com.forum.api.infra.adapter.in.rest.dto.PartidoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@Tag(
    name = "Partidos",
    description = "Gestión de partidos de fútbol"
)
public class PartidoController {
    private final PartidoService partidoService;
    private final SSeRegistrarUseCase registrarUseCase;

    public PartidoController(PartidoService partidoService, SSeRegistrarUseCase registrarUseCase) {
        this.partidoService = partidoService;
        this.registrarUseCase = registrarUseCase;
    }

    @GetMapping("/partidos")
    @Operation(
        summary = "Listar todos los partidos",
        description = "Obtiene una lista completa de todos los partidos registrados en el sistema, incluyendo partidos finalizados, en progreso y programados."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Partidos obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<PartidoResponseDto>> listarTodosLosPartidos() {
        return ResponseEntity.ok().body(partidoService.listarTodosLosPartidos()
                .stream()
                .map(PartidoResponseDto::fromDomainExistent)
                .collect(Collectors.toList()));
    }

    @GetMapping("/partidos/envivo")
    @Operation(
        summary = "Listar partidos en vivo",
        description = "Obtiene una lista de partidos que se encuentran actualmente en vivo (en progreso)."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Partidos en vivo obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<PartidoResponseDto>> listarTodosLosPartidosEnVivo() {
        List<Partido> partidos = partidoService.partidosEnVivo();

        return ResponseEntity.ok().body(
                partidos.stream().
                map(PartidoResponseDto::fromDomainExistent).
                toList()
        );
    }

    @PostMapping("/partidos")
    @Operation(
        summary = "Crear nuevo partido",
        description = "Crea un nuevo partido en el sistema con los equipos especificados. El partido se crea con estado PROGRAMADO."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Partido creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida - Validaciones fallidas"),
        @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o ausente"),
        @ApiResponse(responseCode = "403", description = "Prohibido - El usuario no tiene permisos administrativos"),
        @ApiResponse(responseCode = "404", description = "No encontrado - Uno o ambos equipos no existen"),
        @ApiResponse(responseCode = "409", description = "Conflicto - Partido duplicado"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<PartidoResponseDto> agregarNuevoPartido(@RequestBody PartidoRequestDto partidoRequest) {

        CrearPartidoCommand command = new CrearPartidoCommand(
                partidoRequest.idEquipoLocal(),
                partidoRequest.idEquipoVisitante()
        );

        Partido response = partidoService.guardarPartido(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(location).body(PartidoResponseDto.fromDomainExistent(response));
    }

    @GetMapping(value = "/partidos/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(
        summary = "Stream de actualizaciones en tiempo real",
        description = "Establece una conexión Server-Sent Events (SSE) para recibir actualizaciones en tiempo real de todos los partidos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Conexión SSE establecida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al establecer la conexión SSE")
    })
    public SseEmitter stream(){
        return registrarUseCase.registrar();
    }
}
