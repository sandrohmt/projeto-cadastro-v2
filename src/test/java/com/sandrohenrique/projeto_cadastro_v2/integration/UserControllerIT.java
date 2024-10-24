package com.sandrohenrique.projeto_cadastro_v2.integration;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.repository.UserRepository;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import com.sandrohenrique.projeto_cadastro_v2.util.UserCreator;
import com.sandrohenrique.projeto_cadastro_v2.util.UserPostRequestBodyCreator;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;


    static Stream<String> endpointsProvider() { // Reune todos os endpoits necessarios para serem testados no teste parametrizado
        return Stream.of(
                "/users",
                "/users/byName?name=test",
                "/users/byEmail?email=test@gmail.com",
                "/users/byAge?age=18"
        );
    }

    @Test
    @DisplayName("listAll returns list of user when successful")
    void listAll_ReturnsListOfUser_WhenSuccessful() {
        User saveUser = userRepository.save(UserCreator.createUserToBeSaved());
        String expectedName = saveUser.getName();
        String expectedEmail = saveUser.getEmail();
        Long expectedId = saveUser.getId();
        Float expectedHeight = saveUser.getHeight();

        List<User> users = testRestTemplate.exchange(
                "/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(expectedName).isEqualTo(users.get(0).getName());
        Assertions.assertThat(expectedEmail).isEqualTo(users.get(0).getEmail());
        Assertions.assertThat(expectedId).isEqualTo(users.get(0).getId());
        Assertions.assertThat(expectedHeight).isEqualTo(users.get(0).getHeight());
    }

    @ParameterizedTest
    @MethodSource("endpointsProvider")
    @DisplayName("returns an empty list when no users are found") // Esse método testa os casos ruins de listAll, findByName, findByEmail e findByAge
    void returnsAnEmptyList_WhenNoUsersAreFound(String endpoint) {
        List<User> users = testRestTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findById returns user when successful")
    void findById_ReturnsUser_WhenSuccessful() {
        User savedUser = userRepository.save(UserCreator.createUserToBeSaved());

        Long expectedId = savedUser.getId();

        User user = testRestTemplate.getForObject("/users/{id}", User.class, expectedId);

        Assertions.assertThat(user).isNotNull();

        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns list of user when successful")
    void findByName_ReturnsListOfUser_WhenSuccessful() {
        User savedUser = userRepository.save(UserCreator.createUserToBeSaved());

        String expectedName = savedUser.getName();

        String url = String.format("/users/byName?name=%s", expectedName);

        List<User> users = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByEmail returns list of user when successful")
    void findByEmail_ReturnsListOfUser_WhenSuccessful() {
        User savedUser = userRepository.save(UserCreator.createUserToBeSaved());

        String expectedEmail = savedUser.getEmail();

        String url = String.format("/users/byEmail?email=%s", expectedEmail);

        List<User> users = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getEmail()).isNotNull().isEqualTo(expectedEmail);
    }

    @Test
    @DisplayName("findByAge returns list of user when successful")
    void findByAge_ReturnsListOfUser_WhenSuccessful() {
        User savedUser = userRepository.save(UserCreator.createUserToBeSaved());

        Integer expectedAge = savedUser.getAge();

        String url = String.format("/users/byAge?age=%s", expectedAge);

        List<User> users = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getAge()).isNotNull().isEqualTo(expectedAge);
    }

    @Test
    @DisplayName("save returns user when successful")
    void save_ReturnsUser_WhenSuccessful() {
        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBody();

        ResponseEntity<User> userResponseEntity = testRestTemplate.postForEntity("/users", userPostRequestBody, User.class);

        Assertions.assertThat(userResponseEntity).isNotNull();
        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(userResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(userResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("save throws ConstraintViolationException when user has invalid data")
    void save_ThrowsConstraintViolationException_WhenUserHasInvalidData() {
        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBodyWithoutName();

        User user = testRestTemplate.postForEntity("/users", userPostRequestBody, User.class).getBody();

        Assertions.assertThat(user).isNotNull();

        Assertions.assertThatThrownBy(() -> userRepository.save(user))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("delete removes user when successful")
    void delete_RemovesUser_WhenSuccessFul() {
        User savedUser = userRepository.save(UserCreator.createUserToBeSaved());


        ResponseEntity<Void> userResponseEntity = testRestTemplate.exchange(
                "/users/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                savedUser.getId());

        Assertions.assertThat(userResponseEntity).isNotNull();

        Assertions.assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
