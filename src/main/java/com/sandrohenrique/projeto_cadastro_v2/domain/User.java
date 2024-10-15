package com.sandrohenrique.projeto_cadastro_v2.domain;

import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor // Para o builder funcionar
@NoArgsConstructor
@Entity
@Table(name = "`user`")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
