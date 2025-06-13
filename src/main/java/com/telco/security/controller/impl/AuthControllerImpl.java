package com.telco.security.controller.impl;

import com.telco.security.controller.IAuthController;
import com.telco.security.dto.AuthResponse;
import com.telco.security.dto.LoginRequest;
import com.telco.security.service.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements IAuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final IJwtService jwtService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // 1. Autenticar usando el AuthenticationManager de Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        // 2. Si la autenticación fue exitosa, cargar los detalles del usuario
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());

        // 3. Generar el token JWT
        final String token = jwtService.generateToken(userDetails);

        // 4. Devolver el token en la respuesta
        return ResponseEntity.ok(new AuthResponse(token));
    }
}