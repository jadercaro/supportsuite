package com.telco.security.dto;

/**
 * DTO (Data Transfer Object) para la respuesta de una autenticación exitosa.
 * Contiene el token JWT que el cliente deberá usar para las solicitudes posteriores.
 *
 * @param token El JSON Web Token generado.
 */
public record AuthResponse(
        String token
) {
}