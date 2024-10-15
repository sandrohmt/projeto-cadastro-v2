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

    public static User createUserWithoutName() {
        return User.builder()
                .email("sandrohmt@gmail.com")
                .age(21)
                .height(1.75F)
                .build();
    }

    public static User createUserWithShortName() {
        return User.builder()
                .name("Sandro")
                .email("sandrohmt@gmail.com")
                .age(21)
                .height(1.75F)
                .build();
    }

    public static User createUserWithLongName() {
        return User.builder()
                .name("Taumatawhakatangihangakoauauotamateaturipukakapikimaungahoronukupokaiwhenuakitanatahu")
                .email("sandrohmt@gmail.com")
                .age(21)
                .height(1.75F)
                .build();
    }

    public static User createUserWithoutEmail() {
        return User.builder()
                .name("Sandro Henrique Matos Teixeira")
                .age(21)
                .height(1.75F)
                .build();
    }

    public static User createUserWithoutAge() {
        return User.builder()
                .name("Sandro Henrique Matos Teixeira")
                .email("sandrohmt@gmail.com")
                .height(1.75F)
                .build();
    }


    public static User createUserWithUnderage() {
        return User.builder()
                .name("Sandro Henrique")
                .email("sandrohmt@gmail.com")
                .age(17)
                .height(1.75F)
                .build();
    }

    public static User createUserWithAgeOver120() {
        return User.builder()
                .name("Sandro Henrique")
                .email("sandrohmt@gmail.com")
                .age(121)
                .height(1.75F)
                .build();
    }

    public static User createUserWithoutHeight() {
        return User.builder()
                .id(1L)
                .name("Sandro Henrique Matos Teixeira")
                .age(21)
                .build();
    }

    public static User createUserWithHeightBelow60Centimeters() {
        return User.builder()
                .name("Sandro Henrique Matos Teixeira")
                .email("sandrohmt@gmail.com")
                .age(21)
                .height(0.5F)
                .build();
    }

    public static User createUserWithHeightAbove2_6Meters() {
        return User.builder()
                .name("Sandro Henrique Matos Teixeira")
                .email("sandrohmt@gmail.com")
                .age(21)
                .height(2.7F)
                .build();
    }
}

