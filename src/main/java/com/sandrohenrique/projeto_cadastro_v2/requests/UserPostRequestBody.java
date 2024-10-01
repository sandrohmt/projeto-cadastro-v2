package com.sandrohenrique.projeto_cadastro_v2.requests;

import lombok.Data;

@Data
public class UserPostRequestBody {
    private String name;
    private String email;
    private Integer age;
    private Float height;
}
