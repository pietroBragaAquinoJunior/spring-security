package com.pietro.spring_security.core.ports;

import com.pietro.spring_security.core.domain.UsuarioDomain;

public interface TokenServicePort {
    String generateToken(UsuarioDomain usuarioDomain);
}
