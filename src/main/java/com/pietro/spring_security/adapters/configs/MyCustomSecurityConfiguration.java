package com.pietro.spring_security.adapters.configs;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class MyCustomSecurityConfiguration {

    @Value("classpath:private.pem")
    private Resource privateKeyResource;

    @Value("classpath:public.pem")
    private Resource publicKeyResource;

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    // --- Carrega as chaves PEM da pasta resources ---
    private void loadKeys() throws Exception {
        if (privateKey != null && publicKey != null) return;

        String privateKeyContent = Files.readString(privateKeyResource.getFile().toPath())
                .replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)-----", "")
                .replaceAll("\\s", "");

        String publicKeyContent = Files.readString(publicKeyResource.getFile().toPath())
                .replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)-----", "")
                .replaceAll("\\s", "");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        byte[] privateBytes = Base64.getDecoder().decode(privateKeyContent);
        byte[] publicBytes = Base64.getDecoder().decode(publicKeyContent);

        this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
        this.publicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(publicBytes));
    }

    // --- Configura o JwtEncoder com a chave privada ---
    @Bean
    public JwtEncoder jwtEncoder() throws Exception {
        loadKeys();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID("my-key-id")
                .build();
        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(rsaKey)));
    }

    // --- Configura o JwtDecoder com a chave pública ---
    @Bean
    public JwtDecoder jwtDecoder() throws Exception {
        loadKeys();
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    // --- Configuração de segurança HTTP ---
    @Bean
    public SecurityFilterChain myCustomFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/autenticar/**").permitAll() // Libera o endpoint de autenticação
                .requestMatchers("/h2-console/**").permitAll() // Libera o console do H2
                .requestMatchers("/messages/**").hasAuthority("SCOPE_message:read") // Exemplo protegido
                .anyRequest().authenticated() // O restante precisa de token
            )

            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> {
                    try {
                        jwt.decoder(jwtDecoder());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                })
            );

        return http.build();
    }
}