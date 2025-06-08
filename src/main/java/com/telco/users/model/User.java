package com.telco.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidad JPA que representa a un usuario en la base de datos.
 * Implementa UserDetails para una integración nativa con Spring Security.
 */
@Data // Genera getters, setters, toString, equals, y hashCode
@Builder // Patrón de diseño Builder para crear objetos de forma fluida
@NoArgsConstructor // Constructor sin argumentos requerido por JPA
@AllArgsConstructor // Constructor con todos los argumentos, útil con @Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Almacena el nombre del enum ("ADMIN") en la BD, no el índice (0)
    @Column(nullable = false)
    private Role role;

    // --- Métodos de la interfaz UserDetails ---

    /**
     * Devuelve los permisos concedidos al usuario.
     * Spring Security utiliza esta información para la autorización.
     * El prefijo "ROLE_" es una convención de Spring Security.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    /**
     * Devuelve la contraseña utilizada para autenticar al usuario.
     * ¡Importante! En la BD debe estar hasheada. Spring Security la comparará.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Devuelve el nombre de usuario utilizado para autenticar al usuario.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    // Para este proyecto, asumimos que las cuentas nunca expiran ni se bloquean.
    // Si se necesitara, se podrían añadir campos en la BD para gestionar estos estados.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}