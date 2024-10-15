package com.sandrohenrique.projeto_cadastro_v2.util;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;

public class UserCreator { // Usado para criar instâncias da entidade User
    public static User createUserToBeSaved() {
        return User.builder()
                .name("Sandro Henrique Matos Teixeira")
                .email("sandrohmt@gmail.com")
                .age(21)
                .height(1.75F)
                .build();
    }

    public static User createValidUser() {
        return User.builder()
                .id(1L)
                .name("Sandro Henrique Matos Teixeira")
                .email("sandrohmt@gmail.com")
                .age(21)
                .height(1.75F)
                .build();
    }
}
