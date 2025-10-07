package com.pietro.spring_security.adapters.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pietro.spring_security.adapters.dtos.AutenticarRequestDto;
import com.pietro.spring_security.adapters.mappers.UsuarioMapper;
import com.pietro.spring_security.core.domain.UsuarioDomain;
import com.pietro.spring_security.core.ports.UsuarioServicePort;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/autenticar")
public class AutenticarController {

    private final UsuarioServicePort usuarioServicePort;
    private final UsuarioMapper usuarioMapper;

   public AutenticarController(UsuarioServicePort usuarioServicePort, UsuarioMapper usuarioMapper) {
        this.usuarioServicePort = usuarioServicePort;
        this.usuarioMapper = usuarioMapper;
    }

   @PostMapping("/registrar")
    public ResponseEntity<Map<String, String>> register(@RequestBody AutenticarRequestDto request) {
        UsuarioDomain usuarioDomain = usuarioMapper.toUsuarioDomain(request);
        String token = usuarioServicePort.registrarUsuario(usuarioDomain);
        Map<String, String> response = Map.of("token", token);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/logar")
    public ResponseEntity<Map<String, String>> login(@RequestBody AutenticarRequestDto request) {
        String token = usuarioServicePort.autenticarUsuario(request.getUsername(), request.getPassword());
        Map<String, String> response = Map.of("token", token);
        return ResponseEntity.ok(response);
    }


}
