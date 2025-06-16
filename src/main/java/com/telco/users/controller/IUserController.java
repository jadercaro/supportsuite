package com.telco.users.controller;

import com.telco.users.dto.UpdateUserRoleRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User Management", description = "API para la gestión de usuarios (roles, eliminación, etc.)")
@SecurityRequirement(name = "bearerAuth")
public interface IUserController {

    @Operation(summary = "Actualizar el rol de un usuario", description = "Permite a un administrador cambiar el rol de un usuario existente.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Rol actualizado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Usuario o Rol no encontrado"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            })
    ResponseEntity<Void> updateUserRole(
            @Parameter(description = "ID del usuario a modificar") @PathVariable Long id,
            @Valid @RequestBody UpdateUserRoleRequestDTO requestDTO);

    @Operation(summary = "Eliminar un usuario", description = "Permite a un administrador eliminar un usuario del sistema.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado")
            })
    ResponseEntity<Void> deleteUser(@Parameter(description = "ID del usuario a eliminar") @PathVariable Long id);
}