package com.telco.security.controller;

import com.telco.security.dto.AuthResponse;
import com.telco.security.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Interfaz que define el contrato para el controlador de autenticación.
 */
@Tag(name = "Authentication", description = "API para la autenticación de usuarios y generación de tokens JWT")
public interface IAuthController {

    /**
     * Endpoint para autenticar a un usuario y obtener un token JWT.
     *
     * @param request El DTO con las credenciales del usuario (username y password).
     * @return Una ResponseEntity que contiene el AuthResponse con el token JWT si la autenticación es exitosa.
     */
    @Operation(summary = "Autenticar Usuario (Login)", description = "Proporciona credenciales para recibir un token de acceso JWT.")
    ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request);
}