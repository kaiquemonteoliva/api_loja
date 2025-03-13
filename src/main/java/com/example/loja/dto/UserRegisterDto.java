package com.example.loja.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


public record UserRegisterDto(

      String username,

      String password,

      List<String> roles

) {
    



}
