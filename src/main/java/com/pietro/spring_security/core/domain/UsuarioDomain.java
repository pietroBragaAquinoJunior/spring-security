package com.pietro.spring_security.core.domain;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UsuarioDomain {
    private UUID id;
    private String username;
    private String password;
    private List<String> roles; // Exemplo: ["ROLE_USER", "SCOPE_message:read"]
    
    public UsuarioDomain() {
    }

    public UsuarioDomain(UUID id, String username, String password, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}