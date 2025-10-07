package com.pietro.spring_security.adapters.outbound.persistence.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuario")
// 1. ToString: Exclua todos os relacionamentos.
@ToString(exclude = {"roles"})
// 2. Equals/HashCode: Use apenas o campo @Id, que é estável e único.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private List<String> roles; // Exemplo: ["ROLE_USER", "SCOPE_message:read"]

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());}
    @Override public String getPassword() { return this.password; }
    @Override public String getUsername() { return this.username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
