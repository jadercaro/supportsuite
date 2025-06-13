package com.telco.security.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO (Data Transfer Object) para la solicitud de autenticación.
 * Utiliza Java Record para una definición concisa e inmutable.
 * Incluye validaciones para asegurar que los campos no estén vacíos.
 *
 * @param username El nombre de usuario para el login.
 * @param password La contraseña del usuario.
 */
public record LoginRequest(
        @NotBlank(message = "Username cannot be empty")
        String username,

        @NotBlank(message = "Password cannot be empty")
        String password
) {
}