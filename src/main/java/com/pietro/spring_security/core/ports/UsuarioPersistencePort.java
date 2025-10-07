package com.pietro.spring_security.core.ports;

import com.pietro.spring_security.core.domain.UsuarioDomain;

public interface UsuarioPersistencePort {

    UsuarioDomain registrarUsuario(UsuarioDomain usuarioDomain);

}
