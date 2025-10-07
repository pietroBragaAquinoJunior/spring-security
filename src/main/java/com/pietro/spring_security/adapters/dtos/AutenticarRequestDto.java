package com.pietro.spring_security.adapters.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AutenticarRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}