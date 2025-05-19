package com.telco.users.repository;

import com.telco.users.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long>  {

    /**
     * Busca un usuario por su email.
     *
     * @param email el correo electrónico del usuario a buscar.
     * @return un Optional conteniendo el UserEntity si existe, o vacío si no.
     */
    Optional<UserEntity> findByEmail(String email);
}
