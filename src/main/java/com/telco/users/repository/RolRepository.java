package com.telco.users.repository;

import com.telco.users.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interfaz de Repositorio para la entidad Rol, utilizando Spring Data JPA.
 * Proporciona operaciones CRUD y la capacidad de buscar roles por su nombre.
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    /**
     * Busca un rol específico por su nombre único.
     * Este método será útil para asignar roles a los usuarios al crearlos.
     *
     * @param nombreRol El nombre del rol a buscar (ej. "admin", "tecnico").
     * @return Un Optional que contiene el Rol si se encuentra, o un Optional vacío si no.
     */
    Optional<Rol> findByNombreRol(String nombreRol);

}