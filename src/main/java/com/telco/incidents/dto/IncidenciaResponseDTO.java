package com.telco.incidents.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record IncidenciaResponseDTO(
        Long id,
        SimpleClienteDTO cliente,
        SimpleUserDTO usuarioAsignado,
        String tipoIncidencia,
        String resultadoIncidencia,
        String zona,
        LocalDateTime fecha,
        String descripcion,
        Set<String> etiquetas
) {
}