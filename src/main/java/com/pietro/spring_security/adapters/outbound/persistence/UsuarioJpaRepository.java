package com.pietro.spring_security.adapters.outbound.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pietro.spring_security.adapters.outbound.persistence.entities.UsuarioEntity;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {
    
}
