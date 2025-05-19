package com.telco.users.repository;

import com.telco.users.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    /**
     * Busca un rol por su nombre.
     *
     * @param name el nombre del rol a buscar (por ejemplo, "USER" o "SUPER_ADMIN").
     * @return un Optional con el RoleEntity si existe, o vacío si no.
     */
    Optional<RoleEntity> findByName(String name);
}
