package com.pietro.spring_security.adapters.outbound.persistence;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pietro.spring_security.adapters.mappers.UsuarioMapper;
import com.pietro.spring_security.adapters.outbound.persistence.entities.UsuarioEntity;
import com.pietro.spring_security.core.domain.UsuarioDomain;
import com.pietro.spring_security.core.domain.exceptions.CredenciaisInvalidasException;
import com.pietro.spring_security.core.domain.exceptions.RecursoNaoEncontradoException;
import com.pietro.spring_security.core.ports.UsuarioPersistencePort;

@Service
public class UsuarioPersistencePortImpl implements UsuarioPersistencePort {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioPersistencePortImpl(UsuarioJpaRepository usuarioJpaRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDomain registrarUsuario(UsuarioDomain usuarioDomain) {
        UsuarioEntity UsuarioEntity = usuarioMapper.toUsuarioEntity(usuarioDomain);
        String senhaCriptografada = passwordEncoder.encode(usuarioDomain.getPassword());
        UsuarioEntity.setPassword(senhaCriptografada);
        UsuarioEntity usuarioEntitySalvo = usuarioJpaRepository.save(UsuarioEntity);
        UsuarioDomain UsuarioDomain = usuarioMapper.toUsuarioDomain(usuarioEntitySalvo);
        return UsuarioDomain;
    }

    @Override
    public UsuarioDomain encontrarUsuarioPorUsername(String username) {
        UsuarioEntity usuarioEntity = usuarioJpaRepository.findByUsername(username).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com username: " +username+ ", não encontrado."));
        return usuarioMapper.toUsuarioDomain(usuarioEntity);
    }

    @Override
    public UsuarioDomain autenticar(String username, String password) {
        UsuarioEntity usuarioEntity = usuarioJpaRepository.findByUsername(username).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com username: " +username+ ", não encontrado."));
        if (!passwordEncoder.matches(password, usuarioEntity.getPassword())) {
            throw new CredenciaisInvalidasException("Usuário ou senha inválidos.");
        }
        UsuarioDomain usuarioDomain = usuarioMapper.toUsuarioDomain(usuarioEntity);
        return usuarioDomain;
    }  

}