package com.telco.users.controller;

import com.telco.users.dto.LoginRequestDTO;
import com.telco.users.dto.LoginResponseDTO;
import com.telco.users.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint POST /api/auth/login
     * Recibe un JSON con email y password, valida, genera un JWT y lo devuelve.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
