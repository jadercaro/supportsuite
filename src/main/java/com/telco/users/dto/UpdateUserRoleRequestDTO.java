package com.telco.users.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRoleRequestDTO(
        @NotNull(message = "El ID del nuevo rol no puede ser nulo.")
        Long newRoleId
) {
}