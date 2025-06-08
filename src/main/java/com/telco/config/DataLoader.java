package com.telco.config;

import com.telco.users.model.Role;
import com.telco.users.model.User;
import com.telco.users.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Solo cargar datos si no existen usuarios para evitar duplicados en reinicios
        if (userRepository.count() == 0) {
            System.out.println("Cargando datos de prueba...");

            User supervisor = User.builder()
                    .firstName("Juan")
                    .lastName("Perez")
                    .email("supervisor@telconova.com")
                    .username("supervisor")
                    .password(passwordEncoder.encode("password123")) // ¡Contraseña hasheada!
                    .role(Role.SUPERVISOR)
                    .build();

            userRepository.save(supervisor);

            System.out.println("Usuario de prueba 'supervisor' creado con contraseña 'password123'");
        }
    }
}