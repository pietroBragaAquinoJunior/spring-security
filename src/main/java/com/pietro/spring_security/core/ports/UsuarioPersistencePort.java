package com.pietro.spring_security.core.ports;

import com.pietro.spring_security.core.domain.UsuarioDomain;

public interface UsuarioPersistencePort {

    UsuarioDomain registrarUsuario(UsuarioDomain usuarioDomain);

    UsuarioDomain encontrarUsuarioPorUsername(String username);

    UsuarioDomain autenticar(String username, String password);

}
