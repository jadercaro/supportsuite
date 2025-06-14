package com.telco.users.repository;

import com.telco.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz de Repositorio para la entidad User, utilizando Spring Data JPA.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Busca un usuario por su nombre de usuario (username).
     *
     * @param username El nombre de usuario único a buscar.
     * @return Un Optional<User> que contendrá al usuario si se encuentra,
     *         o estará vacío si no existe un usuario con ese username.
     */
    Optional<User> findByEmail(String username);

}