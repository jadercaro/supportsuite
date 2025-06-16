package com.telco.users.service.impl;

import com.telco.users.repository.IUserRepository;
import com.telco.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telco.users.model.Rol;
import com.telco.users.repository.RolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.telco.users.model.User;

/**
 * Implementación del servicio de usuarios.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final RolRepository rolRepository;

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

    @Override
    @Transactional
    public User updateUserRole(Long userId, Long newRoleId) {
        // 1. Buscar el usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + userId));

        // 2. Buscar el nuevo rol
        Rol newRol = rolRepository.findById(newRoleId)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con ID: " + newRoleId));

        // 3. Actualizar el rol y guardar
        user.setRol(newRol);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        // 1. Verificar si el usuario existe antes de intentar borrarlo
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + userId);
        }
        // 2. Eliminar el usuario
        userRepository.deleteById(userId);
    }
}