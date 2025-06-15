package com.telco.incidents.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record IncidenciaRequestDTO(
        @NotNull(message = "El ID del cliente no puede ser nulo.")
        Long clienteId,

        @NotNull(message = "El ID del usuario (técnico) no puede ser nulo.")
        Long usuarioId,

        @NotNull(message = "El ID del tipo de incidencia no puede ser nulo.")
        Long tipoId,

        @NotNull(message = "El ID de la zona no puede ser nulo.")
        Long zonaId,

        // El resultado es opcional al crear, se asignará después.
        Long resultadoId,

        @NotBlank(message = "La descripción no puede estar vacía.")
        @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres.")
        String descripcion,

        // Las etiquetas son opcionales. Se envían los IDs de las etiquetas existentes.
        Set<Long> etiquetaIds
) {
}