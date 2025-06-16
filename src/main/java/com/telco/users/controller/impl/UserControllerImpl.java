package com.telco.users.controller.impl;

import com.telco.users.controller.IUserController;
import com.telco.users.dto.UpdateUserRoleRequestDTO;
import com.telco.users.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserControllerImpl implements IUserController {

    private final IUserService userService;

    @Override
    @PatchMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateUserRole(@PathVariable Long id, @Valid @RequestBody UpdateUserRoleRequestDTO requestDTO) {
        userService.updateUserRole(id, requestDTO.newRoleId());
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }
}