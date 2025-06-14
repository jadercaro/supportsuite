package com.telco.users.service.impl;

import com.telco.users.repository.IUserRepository;
import com.telco.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de usuarios.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;

    /**
     * Implementación del método de la interfaz UserDetailsService.
     * Es invocado por Spring Security para cargar un usuario por su nombre de usuario
     * durante el proceso de autenticación.
     *
     * @param username El nombre de usuario a buscar.
     * @return Los detalles del usuario (nuestra entidad User que implementa UserDetails).
     * @throws UsernameNotFoundException si el usuario no se encuentra en la base de datos.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found."));
    }

    // Aquí irían las implementaciones de los métodos definidos en IUserService.
}