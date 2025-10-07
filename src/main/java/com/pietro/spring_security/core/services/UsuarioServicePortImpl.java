package com.pietro.spring_security.core.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pietro.spring_security.core.domain.UsuarioDomain;
import com.pietro.spring_security.core.ports.UsuarioPersistencePort;
import com.pietro.spring_security.core.ports.UsuarioServicePort;

@Service
public class UsuarioServicePortImpl implements UsuarioServicePort {

    private final UsuarioPersistencePort usuarioPersistencePort;
    private final TokenServicePortImpl tokenServicePortImpl;

    public UsuarioServicePortImpl(UsuarioPersistencePort usuarioPersistencePort, TokenServicePortImpl tokenServicePortImpl) {
        this.usuarioPersistencePort = usuarioPersistencePort;
        this.tokenServicePortImpl = tokenServicePortImpl;
    }

    @Override
    public String registrarUsuario(UsuarioDomain usuarioDomain) {
        usuarioDomain.setRoles(List.of("ROLE_USER", "SCOPE_message:read"));
        UsuarioDomain usuarioDomainSalvo = usuarioPersistencePort.registrarUsuario(usuarioDomain);
        return tokenServicePortImpl.generateToken(usuarioDomainSalvo);
    }
    
}
