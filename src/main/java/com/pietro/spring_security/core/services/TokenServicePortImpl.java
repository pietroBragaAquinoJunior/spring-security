package com.pietro.spring_security.core.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import com.pietro.spring_security.core.domain.UsuarioDomain;
import com.pietro.spring_security.core.ports.TokenServicePort;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenServicePortImpl implements TokenServicePort {

    private final JwtEncoder encoder;

    @Value("${jwt.issuer.uri}")
    private String issuerUri;

    public TokenServicePortImpl(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String generateToken(UsuarioDomain usuarioDomain) {
        Instant now = Instant.now();
        long expiry = 3600L; // 1 hour

        String scopes = usuarioDomain.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuerUri)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(usuarioDomain.getUsername())
                .claim("scope", scopes)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();

        return encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}