package com.telco.security.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello-public")
    public ResponseEntity<String> helloPublic() {
        return ResponseEntity.ok("Hola! Este es un endpoint público.");
    }

    @GetMapping("/hello-supervisor")
    @PreAuthorize("hasRole('SUPERVISOR')") // ¡Solo un SUPERVISOR puede acceder!
    public ResponseEntity<String> helloSupervisor() {
        return ResponseEntity.ok("Hola, Supervisor! Has accedido a un recurso protegido.");
    }
}