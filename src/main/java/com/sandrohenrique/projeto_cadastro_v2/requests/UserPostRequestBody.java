package com.sandrohenrique.projeto_cadastro_v2.requests;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserPostRequestBody {
    @NotEmpty(message = "The user cannot be empty")
    @Length(min = 10, max = 50)
    private String name;
    @NotEmpty(message = "The email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "The age cannot be null") // NotNull pois int e floats ja nao podem ser vazios
    @Min(18)
    @Max(120)
    private Integer age;
    @NotNull(message = "The height cannot be null") // NotNull pois int e floats ja nao podem ser vazios
    @Min(1)
    @Max(3)
    private Float height;
}
