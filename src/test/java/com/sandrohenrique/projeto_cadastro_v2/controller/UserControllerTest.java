package com.sandrohenrique.projeto_cadastro_v2.controller;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.exception.BadRequestException;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import com.sandrohenrique.projeto_cadastro_v2.service.UserService;
import com.sandrohenrique.projeto_cadastro_v2.util.UserCreator;
import com.sandrohenrique.projeto_cadastro_v2.util.UserPostRequestBodyCreator;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    UserService userServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userServiceMock.listAll())
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(UserCreator.createValidUser());

        BDDMockito.when(userServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userServiceMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userServiceMock.findByAge(ArgumentMatchers.anyInt()))
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userServiceMock.save(ArgumentMatchers.any(UserPostRequestBody.class)))
                .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.doNothing().when(userServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("listAll returns list of user when successful")
    void listAll_ReturnsListOfUser_WhenSuccessful() {
        User user = UserCreator.createValidUser();
        String expectedName = user.getName();
        String expectedEmail = user.getEmail();
        Long expectedId = user.getId();
        Float expectedHeight = user.getHeight();

        List<User> users = userController.listAll().getBody();

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
        BDDMockito.when(userServiceMock.listAll())
                .thenReturn(Collections.emptyList());

        List<User> users = userController.listAll().getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findById returns user when successful")
    void findById_ReturnsUser_WhenSuccessful() {
        Long expectedId = UserCreator.createValidUser().getId();

        User user = userController.findById(1L).getBody();

        Assertions.assertThat(user).isNotNull();

        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById throw BadRequestException when user is not found")
    void findById_ThrowBadRequestException_WhenUserIsNotFound() {
        BDDMockito.when(userServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenThrow(BadRequestException.class);

        Assertions.assertThatThrownBy(() -> userController.findById(1L))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("findByName returns list of user when successful")
    void findByName_ReturnsListOfUser_WhenSuccessful() {
        String expectedName = UserCreator.createValidUser().getName();

        List<User> users = userController.findByName(expectedName).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list when user is not found")
    void findByName_ReturnsEmptyList_WhenUserIsNotFound() {
        BDDMockito.when(userServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<User> users = userController.findByName("user").getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findByEmail returns list of user when successful")
    void findByEmail_ReturnsListOfUser_WhenSuccessful() {
        String expectedEmail = UserCreator.createValidUser().getEmail();

        List<User> users = userController.findByEmail(expectedEmail).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getEmail()).isNotNull().isEqualTo(expectedEmail);
    }

    @Test
    @DisplayName("findByEmail returns an empty list when user is not found")
    void findByEmail_ReturnsEmptyList_WhenUserIsNotFound() {
        BDDMockito.when(userServiceMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<User> users = userController.findByEmail("user@gmail.com").getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findByAge returns list of user when successful")
    void findByAge_ReturnsListOfUser_WhenSuccessful() {
        Integer expectedAge = UserCreator.createValidUser().getAge();

        List<User> users = userController.findByage(expectedAge).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getAge()).isNotNull().isEqualTo(expectedAge);
    }

    @Test
    @DisplayName("findByAge returns an empty list when user is not found")
    void findByAge_ReturnsEmptyList_WhenUserIsNotFound() {
        BDDMockito.when(userServiceMock.findByAge(ArgumentMatchers.anyInt()))
                .thenReturn(Collections.emptyList());

        List<User> users = userController.findByage(18).getBody();

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns user when successful")
    void save_ReturnsUser_WhenSuccessful() {
        User user = userController.save(UserPostRequestBodyCreator.createUserPostRequestBody()).getBody();

        Assertions.assertThat(user)
                .isNotNull()
                .isEqualTo(UserCreator.createUserToBeSaved());
    }

    @Test
    @DisplayName("save throws ConstraintViolationException when user has invalid data")
     void save_ThrowsConstraintViolationException_WhenUserHasInvalidData() {
        BDDMockito.when(userServiceMock.save(ArgumentMatchers.any(UserPostRequestBody.class)))
                .thenThrow(ConstraintViolationException.class);

        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBody();

        Assertions.assertThatThrownBy(() -> userController.save(userPostRequestBody))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("delete removes user when successful")
    void delete_RemovesUser_WhenSuccessFul() {
        Assertions.assertThatCode(() -> userController.delete(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = userController.delete(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete throws ConstraintViolationException when user is not found")
    void delete_ThrowsConstraintViolationException_WhenUserIsNotFound() {
        BDDMockito.doThrow(BadRequestException.class)
                .when(userServiceMock).delete(ArgumentMatchers.anyLong());

        Assertions.assertThatThrownBy(() -> userController.delete(1L))
                .isInstanceOf(BadRequestException.class);
    }
}