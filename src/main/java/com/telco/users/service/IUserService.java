package com.telco.users.service;

import com.telco.users.model.User;

/**
 * Interfaz que define el contrato para los servicios de negocio relacionados con los usuarios.
 * Abstrae la lógica de negocio de la implementación concreta, permitiendo que otras
 * partes de la aplicación dependan de esta interfaz en lugar de la clase de implementación.
 */
public interface IUserService {

    /**
     * Actualiza el rol de un usuario existente.
     * @param userId El ID del usuario a modificar.
     * @param newRoleId El ID del nuevo rol a asignar.
     * @return El usuario actualizado.
     */
    User updateUserRole(Long userId, Long newRoleId);

    /**
     * Elimina un usuario del sistema.
     * @param userId El ID del usuario a eliminar.
     */
    void deleteUser(Long userId);
}