package com.sandrohenrique.projeto_cadastro_v2.repository;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.util.UserCreator;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
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
    @DisplayName("findByName returns an empty list when user is not found")
    void findByName_ReturnsEmptyListOfUser_WhenUserIsNotFound() {
        List<User> users = this.userRepository.findByName("test");

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
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

    @Test
    @DisplayName("findByEmail returns an empty list when user is not found")
    void findByEmail_ReturnsEmptyListOfUser_WhenUserIsNotFound() {
        List<User> users = this.userRepository.findByEmail("test@gmail.com");

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
        User user = UserCreator.createUserWithoutName();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is lesser than 10 characters")
    void save_ThrowsConstraintViolationException_WhenNameIsLesserThan10Characters() {
        User user = UserCreator.createUserWithShortName();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is greater than 50 characters")
    void save_ThrowsConstraintViolationException_WhenNameIsGreaterThan50Characters() {
        User user = UserCreator.createUserWithLongName();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when email is empty")
    void save_ThrowsConstraintViolationException_WhenEmailIsEmpty() {
        User user = UserCreator.createUserWithoutEmail();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when age is null")
    void save_ThrowsConstraintViolationException_WhenAgeIsNull() {
        User user = UserCreator.createUserWithoutAge();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when age is under 18")
    void save_ThrowsConstraintViolationException_WhenAgeIsUnder18() {
        User user = UserCreator.createUserWithUnderage();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when age is over 120")
    void save_ThrowsConstraintViolationException_WhenAgeIsOver120() {
        User user = UserCreator.createUserWithAgeOver120();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when height is null")
    void save_ThrowsConstraintViolationException_WhenHeightIsNull() {
        User user = UserCreator.createUserWithoutHeight();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when height is below 60 centimeters")
    void save_ThrowsConstraintViolationException_WhenHeightIsBelow60Centimeters() {
        User user = UserCreator.createUserWithHeightBelow60Centimeters();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when height is above 2.6 meters")
    void save_ThrowsConstraintViolationException_WhenHeightIsAbove2_6Meters() {
        User user = UserCreator.createUserWithHeightAbove2_6Meters();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.userRepository.save(user));
    }
}