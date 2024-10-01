package com.sandrohenrique.projeto_cadastro_v2.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserPostRequestBody {
    @NotEmpty(message = "The user cannot be empty")
    private String name;
    @NotEmpty(message = "The email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "The age cannot be empty") // NotNull pois int e floats ja nao podem ser vazios
    @Min(16)
    @Max(120)
    private Integer age;
    @NotNull(message = "The height cannot be empty") // NotNull pois int e floats ja nao podem ser vazios
    @Min(1)
    @Max(3)
    private Float height;
}
