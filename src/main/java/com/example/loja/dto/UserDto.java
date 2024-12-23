package com.example.loja.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(

        @NotBlank String email,

        @NotNull String password

) {
}
