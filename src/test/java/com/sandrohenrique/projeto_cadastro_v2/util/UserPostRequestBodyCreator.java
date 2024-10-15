package com.sandrohenrique.projeto_cadastro_v2.util;

import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;

public class UserPostRequestBodyCreator { // Usado para criar instâncias da entidade UserPostRequestBody, que é o objeto da requisição. Usado em testes de controladores e serviços que manipulam requisições
    public static UserPostRequestBody createUserPostRequestBody() {
        return UserPostRequestBody.builder()
                .name(UserCreator.createUserToBeSaved().getName())
                .email(UserCreator.createUserToBeSaved().getEmail())
                .age(UserCreator.createUserToBeSaved().getAge())
                .height(UserCreator.createUserToBeSaved().getHeight())
                .build();
    }
}
