package com.telco.incidents.controller;

import com.telco.incidents.dto.IncidenciaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Incidents Management", description = "API para la gestión y consulta de incidencias")
public interface IIncidenciaController {

    @Operation(summary = "Buscar y filtrar incidencias",
            description = "Obtiene una lista paginada de incidencias. Todos los filtros son opcionales. " +
                    "El acceso está restringido a usuarios con el rol 'ADMIN'.")
    ResponseEntity<Page<IncidenciaResponseDTO>> searchIncidents(
            @Parameter(description = "Filtrar por ID de incidencia") @RequestParam(required = false) Long id,
            @Parameter(description = "Filtrar por ID del técnico asignado") @RequestParam(required = false) Long usuarioId,
            @Parameter(description = "Filtrar por ID del tipo de incidencia") @RequestParam(required = false) Long tipoId,
            @Parameter(description = "Filtrar por ID del resultado de la incidencia") @RequestParam(required = false) Long resultadoId,
            @Parameter(description = "Paginación y ordenamiento (ej. page=0&size=10&sort=fecha,desc)") Pageable pageable
    );
}