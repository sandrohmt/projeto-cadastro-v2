package com.sandrohenrique.projeto_cadastro_v2.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
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
    @DecimalMin(value = "0.6")
    @DecimalMax(value = "2.6")
    private Float height;
}
