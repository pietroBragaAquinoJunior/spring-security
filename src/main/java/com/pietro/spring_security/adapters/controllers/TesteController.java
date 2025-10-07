package com.pietro.spring_security.adapters.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    // Endpoint que exige uma role específica
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Bem-vindo, ADMIN 👑 — você tem a role necessária!");
    }

    // Endpoint que exige um escopo específico (exemplo de OAuth2 scope)
    @PreAuthorize("hasAuthority('SCOPE_message:read')")
    @GetMapping("/escopo")
    public ResponseEntity<String> comEscopo() {
        return ResponseEntity.ok("Acesso concedido ao escopo 'message:read' 📜");
    }

}