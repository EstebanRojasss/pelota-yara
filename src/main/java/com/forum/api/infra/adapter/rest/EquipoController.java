package com.forum.api.infra.adapter.rest;

import com.forum.api.application.in.EquipoService;
import com.forum.api.domain.Equipo;
import com.forum.api.infra.adapter.rest.dto.EquipoRequestDto;
import com.forum.api.infra.adapter.rest.dto.EquipoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EquipoController {

    private final EquipoService service;

    public EquipoController(EquipoService service) {
        this.service = service;
    }


    @GetMapping("/equipos")
    public ResponseEntity<List<EquipoResponseDto>> listarTodosLosEquipos(){
        return ResponseEntity.ok().body(
                service.listarTodosLosEquipos()
                        .stream()
                        .map(EquipoResponseDto::fromDomain)
                        .collect(Collectors.toList()));
    }
    @PostMapping("/equipos")
    public ResponseEntity<EquipoResponseDto> agregarNuevoEquipo(@RequestBody EquipoRequestDto requestDto){
        Equipo equipo = service.agregarNuevoEquipo(requestDto.toDomain());

        return ResponseEntity.ok().body(EquipoResponseDto.fromDomain(equipo));
    }
}
