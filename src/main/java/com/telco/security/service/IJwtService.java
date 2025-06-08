package com.telco.security.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interfaz que define el contrato para el servicio de gestión de JSON Web Tokens (JWT).
 * Proporciona una abstracción para generar, validar y extraer información de los tokens.
 */
public interface IJwtService {

    /**
     * Extrae el nombre de usuario (subject) de un token JWT.
     *
     * @param token El token JWT.
     * @return El nombre de usuario contenido en el token.
     */
    String extractUsername(String token);

    /**
     * Genera un nuevo token JWT para un usuario.
     *
     * @param userDetails Los detalles del usuario (obtenidos de Spring Security) para quien se genera el token.
     * @return El token JWT como una cadena de texto.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Valida si un token JWT es válido para un usuario específico.
     * La validación incluye verificar la firma, la fecha de expiración y si el
     * sujeto del token corresponde al usuario.
     *
     * @param token       El token JWT a validar.
     * @param userDetails Los detalles del usuario contra los que se valida el token.
     * @return {@code true} si el token es válido, {@code false} en caso contrario.
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}