package com.telco.users.service;

import com.telco.users.dto.LoginRequestDTO;
import com.telco.users.dto.LoginResponseDTO;
import com.telco.users.model.UserEntity;
import com.telco.users.repository.IUserRepository;
import com.telco.util.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthService(IUserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public LoginResponseDTO authenticate(LoginRequestDTO request) {
        // 1. Buscar usuario por email
        Optional<UserEntity> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Credenciales inválidas"); // En producción usar excepción más específica
        }
        UserEntity user = userOpt.get();

        // 2. Verificar contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Generar token JWT y fecha de expiración
        String token = jwtTokenUtil.generateToken(user.getEmail());
        Instant expiresAt = jwtTokenUtil.getExpirationDateFromToken(token);

        // 4. Retornar el DTO con token + expiración
        return new LoginResponseDTO(token, expiresAt);
    }
}
