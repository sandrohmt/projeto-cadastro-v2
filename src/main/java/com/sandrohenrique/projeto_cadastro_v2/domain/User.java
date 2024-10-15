package com.sandrohenrique.projeto_cadastro_v2.domain;

import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;
    private String email;
    private Integer age;
    private Float height;

}
