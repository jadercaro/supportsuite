package com.telco.incidents.controller.impl;

import com.telco.incidents.controller.IIncidenciaController;
import com.telco.incidents.dto.IncidenciaResponseDTO;
import com.telco.incidents.mapper.IncidenciaMapper;
import com.telco.incidents.model.Incidencia;
import com.telco.incidents.service.IIncidenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidenciaControllerImpl implements IIncidenciaController {

    private final IIncidenciaService incidenciaService;
    private final IncidenciaMapper incidenciaMapper;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // Solo los ADMINS pueden acceder.
    public ResponseEntity<Page<IncidenciaResponseDTO>> searchIncidents(
            Long id,
            Long usuarioId,
            Long tipoId,
            Long resultadoId,
            Pageable pageable) {

        // 1. Llamar al servicio para obtener la página de entidades
        Page<Incidencia> incidenciaPage = incidenciaService.searchIncidents(
                id, usuarioId, tipoId, resultadoId, pageable
        );

        // 2. Mapear la página de entidades a una página de DTOs
        Page<IncidenciaResponseDTO> dtoPage = incidenciaPage.map(incidenciaMapper::toDto);

        // 3. Devolver la página de DTOs en una respuesta 200 OK
        return ResponseEntity.ok(dtoPage);
    }
}