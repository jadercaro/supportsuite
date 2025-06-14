package com.telco.config;

import com.telco.users.model.Rol;
import com.telco.users.model.User;
import com.telco.users.repository.IUserRepository;
import com.telco.users.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    // --- DEPENDENCIAS INYECTADAS ---
    // Añadimos IUserRepository y PasswordEncoder como campos finales
    // para que @RequiredArgsConstructor los incluya en el constructor.
    private final IUserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Cargar roles solo si la tabla está vacía
        if (rolRepository.count() == 0) {
            System.out.println("Cargando roles de prueba...");

            // Crear y guardar el rol de administrador
            Rol adminRol = new Rol();
            adminRol.setNombreRol("admin");
            rolRepository.save(adminRol);

            // Crear y guardar el rol de técnico
            Rol tecnicoRol = new Rol();
            tecnicoRol.setNombreRol("tecnico");
            rolRepository.save(tecnicoRol);

            // Crear y guardar el rol de consultor
            Rol consultorRol = new Rol();
            consultorRol.setNombreRol("consultor");
            rolRepository.save(consultorRol);

            System.out.println("Roles de prueba cargados: admin, tecnico, consultor");
        }

        // Cargar un usuario de prueba solo si la tabla de usuarios está vacía
        if (userRepository.count() == 0) {
            System.out.println("Cargando usuario de prueba...");

            // Buscar el rol 'admin' que acabamos de crear.
            // .orElseThrow() lanzará una excepción si el rol no se encuentra, lo cual es bueno para depurar.
            Rol rolParaUsuario = rolRepository.findByNombreRol("admin")
                    .orElseThrow(() -> new RuntimeException("Error: Rol 'admin' no encontrado en la base de datos."));

            // Crear el usuario de prueba
            User supervisor = User.builder()
                    .firstName("Juan Supervisor")
                    .email("supervisor@telconova.com")
                    .password(passwordEncoder.encode("password123")) // Ahora passwordEncoder se resuelve
                    .rol(rolParaUsuario)
                    .build();

            // Guardar el usuario en la base de datos
            userRepository.save(supervisor); // Ahora userRepository se resuelve

            System.out.println("Usuario de prueba 'supervisor@telconova.com' creado con rol 'admin'.");
        }
    }
}