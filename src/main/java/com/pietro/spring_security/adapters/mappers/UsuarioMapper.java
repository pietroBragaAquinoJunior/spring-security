package com.pietro.spring_security.adapters.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.pietro.spring_security.adapters.dtos.AutenticarRequestDto;
import com.pietro.spring_security.adapters.dtos.AutenticarResponseDto;
import com.pietro.spring_security.adapters.outbound.persistence.entities.UsuarioEntity;
import com.pietro.spring_security.core.domain.UsuarioDomain;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    // Response - Domain
    @Mapping(target = "token", ignore = true )
    AutenticarResponseDto toAutenticarResponseDto(UsuarioDomain UsuarioDomain);
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "username", ignore = true )
    @Mapping(target = "password", ignore = true )
    @Mapping(target = "roles", ignore = true )
    @Mapping(target = "authorities", ignore = true )
    UsuarioDomain toUsuarioDomain(AutenticarResponseDto AutenticarResponseDto);
    // Request - Domain
    AutenticarRequestDto toAutenticarRequestDto(UsuarioDomain UsuarioDomain);
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "roles", ignore = true )
    @Mapping(target = "authorities", ignore = true )
    UsuarioDomain toUsuarioDomain(AutenticarRequestDto AutenticarRequestDto);
    // Entity - Domain
    UsuarioEntity toUsuarioEntity(UsuarioDomain UsuarioDomain);
    UsuarioDomain toUsuarioDomain(UsuarioEntity UsuarioEntity);
}