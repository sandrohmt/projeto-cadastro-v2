package com.sandrohenrique.projeto_cadastro_v2.integration;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.repository.UserRepository;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import com.sandrohenrique.projeto_cadastro_v2.util.UserCreator;
import com.sandrohenrique.projeto_cadastro_v2.util.UserPostRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

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

    @Test
    @DisplayName("listAll returns an empty list when no users are found")
    void listAll_ReturnsAnEmptyList_WhenNoUsersAreFound() {
        List<User> users = testRestTemplate.exchange(
                "/users",
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

//    @Test
//    @DisplayName("findById throw BadRequestException when user is not found")
//    void findById_ThrowBadRequestException_WhenUserIsNotFound() {
//        BDDMockito.when(userServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
//                .thenThrow(BadRequestException.class);
//
//        Assertions.assertThatThrownBy(() -> userController.findById(1L))
//                .isInstanceOf(BadRequestException.class);
//    }
//
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
    @DisplayName("findByName returns an empty list when user is not found")
    void findByName_ReturnsEmptyList_WhenUserIsNotFound() {
        List<User> users = testRestTemplate.exchange(
                "/users/byName?name=test",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                }).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

//    @Test
//    @DisplayName("findByEmail returns list of user when successful")
//    void findByEmail_ReturnsListOfUser_WhenSuccessful() {
//        String expectedEmail = UserCreator.createValidUser().getEmail();
//
//        List<User> users = userController.findByEmail(expectedEmail).getBody();
//
//        Assertions.assertThat(users)
//                .isNotNull()
//                .hasSize(1);
//
//        Assertions.assertThat(users.get(0).getEmail()).isNotNull().isEqualTo(expectedEmail);
//    }
//
//    @Test
//    @DisplayName("findByEmail returns an empty list when user is not found")
//    void findByEmail_ReturnsEmptyList_WhenUserIsNotFound() {
//        BDDMockito.when(userServiceMock.findByEmail(ArgumentMatchers.anyString()))
//                .thenReturn(Collections.emptyList());
//
//        List<User> users = userController.findByEmail("user@gmail.com").getBody();
//
//        Assertions.assertThat(users)
//                .isNotNull()
//                .isEmpty();
//    }
//
//    @Test
//    @DisplayName("findByAge returns list of user when successful")
//    void findByAge_ReturnsListOfUser_WhenSuccessful() {
//        Integer expectedAge = UserCreator.createValidUser().getAge();
//
//        List<User> users = userController.findByAge(expectedAge).getBody();
//
//        Assertions.assertThat(users)
//                .isNotNull()
//                .hasSize(1);
//
//        Assertions.assertThat(users.get(0).getAge()).isNotNull().isEqualTo(expectedAge);
//    }
//
//    @Test
//    @DisplayName("findByAge returns an empty list when user is not found")
//    void findByAge_ReturnsEmptyList_WhenUserIsNotFound() {
//        BDDMockito.when(userServiceMock.findByAge(ArgumentMatchers.anyInt()))
//                .thenReturn(Collections.emptyList());
//
//        List<User> users = userController.findByAge(18).getBody();
//
//        Assertions.assertThat(users)
//                .isNotNull()
//                .isEmpty();
//    }
//
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
//
//    @Test
//    @DisplayName("save throws ConstraintViolationException when user has invalid data")
//    void save_ThrowsConstraintViolationException_WhenUserHasInvalidData() {
//        BDDMockito.when(userServiceMock.save(ArgumentMatchers.any(UserPostRequestBody.class)))
//                .thenThrow(ConstraintViolationException.class);
//
//        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBody();
//
//        Assertions.assertThatThrownBy(() -> userController.save(userPostRequestBody))
//                .isInstanceOf(ConstraintViolationException.class);
//    }
//
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

//    @Test
//    @DisplayName("delete throws ConstraintViolationException when user is not found")
//    void delete_ThrowsConstraintViolationException_WhenUserIsNotFound() {
//        BDDMockito.doThrow(BadRequestException.class)
//                .when(userServiceMock).delete(ArgumentMatchers.anyLong());
//
//        Assertions.assertThatThrownBy(() -> userController.delete(1L))
//                .isInstanceOf(BadRequestException.class);
//    }
}
