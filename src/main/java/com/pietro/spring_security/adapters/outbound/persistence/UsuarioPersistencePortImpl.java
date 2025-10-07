package com.pietro.spring_security.adapters.outbound.persistence;

import org.springframework.stereotype.Service;

import com.pietro.spring_security.adapters.mappers.UsuarioMapper;
import com.pietro.spring_security.adapters.outbound.persistence.entities.UsuarioEntity;
import com.pietro.spring_security.core.domain.UsuarioDomain;
import com.pietro.spring_security.core.ports.UsuarioPersistencePort;

@Service
public class UsuarioPersistencePortImpl implements UsuarioPersistencePort {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioPersistencePortImpl(UsuarioJpaRepository usuarioJpaRepository, UsuarioMapper usuarioMapper) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioDomain registrarUsuario(UsuarioDomain usuarioDomain) {
        UsuarioEntity UsuarioEntity = usuarioMapper.toUsuarioEntity(usuarioDomain);
        UsuarioEntity usuarioEntitySalvo = usuarioJpaRepository.save(UsuarioEntity);
        UsuarioDomain UsuarioDomain = usuarioMapper.toUsuarioDomain(usuarioEntitySalvo);
        return UsuarioDomain;
    }  

}