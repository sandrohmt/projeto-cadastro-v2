package com.sandrohenrique.projeto_cadastro_v2.repository;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.util.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest()
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("findByName returns a list of user when successful")
    void findByName_ReturnsListOfUser_WhenSuccessful() {
        User userToBeSaved = UserCreator.createUserToBeSaved();

        User userSaved = this.userRepository.save(userToBeSaved);

        String name = userSaved.getName();

        List<User> users = this.userRepository.findByName(name);

        Assertions.assertThat(users)
                .isNotNull()
                .isNotEmpty()
                .contains(userSaved);
    }

    @Test
    @DisplayName("findByEmail returns a list of user when successful")
    void findByEmail_ReturnsListOfUser_WhenSuccessful() {
        User userToBeSaved = UserCreator.createUserToBeSaved();

        User userSaved = this.userRepository.save(userToBeSaved);

        String email = userSaved.getEmail();

        List<User> users = this.userRepository.findByEmail(email);

        Assertions.assertThat(users)
                .isNotNull()
                .isNotEmpty()
                .contains(userSaved);
    }
}