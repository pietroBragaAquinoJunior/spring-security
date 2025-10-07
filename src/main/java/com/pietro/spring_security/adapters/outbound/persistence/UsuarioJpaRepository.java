package com.pietro.spring_security.adapters.outbound.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pietro.spring_security.adapters.outbound.persistence.entities.UsuarioEntity;
import java.util.Optional;


public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {
    
    Optional<UsuarioEntity> findByUsername(String username);

}
