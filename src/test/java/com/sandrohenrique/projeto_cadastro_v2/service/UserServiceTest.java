package com.sandrohenrique.projeto_cadastro_v2.service;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.exception.BadRequestException;
import com.sandrohenrique.projeto_cadastro_v2.mapper.UserMapper;
import com.sandrohenrique.projeto_cadastro_v2.repository.UserRepository;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    UserMapper userMapperMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userRepositoryMock.findAll())
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(UserCreator.createValidUser()));

        BDDMockito.when(userRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userRepositoryMock.findByAge(ArgumentMatchers.anyInt()))
                .thenReturn(List.of(UserCreator.createValidUser()));

        BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class)))
                .thenReturn(UserCreator.createUserToBeSaved());

        BDDMockito.doNothing().when(userRepositoryMock).delete(ArgumentMatchers.any(User.class));
    }

    @Test
    @DisplayName("listAll returns list of user when successful")
    void listAll_ReturnsListOfUser_WhenSuccessful() {
        User user = UserCreator.createValidUser();
        String expectedName = user.getName();
        String expectedEmail = user.getEmail();
        Long expectedId = user.getId();
        Float expectedHeight = user.getHeight();

        List<User> users = userService.listAll();

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
        BDDMockito.when(userRepositoryMock.findAll())
                .thenReturn(Collections.emptyList());

        List<User> users = userService.listAll();

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns user when successful")
    void findByIdOrThrowBadRequestException_ReturnsUser_WhenSuccessful() {
        Long expectedId = UserCreator.createValidUser().getId();

        User user = userService.findByIdOrThrowBadRequestException(1L);

        Assertions.assertThat(user).isNotNull();

        Assertions.assertThat(user.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throw BadRequestException when user is not found")
    void findByIdOrThrowBadRequestException_ThrowBadRequestException_WhenUserIsNotFound() {
        BDDMockito.when(userRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.findByIdOrThrowBadRequestException(1L))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("findByName returns list of user when successful")
    void findByName_ReturnsListOfUser_WhenSuccessful() {
        String expectedName = UserCreator.createValidUser().getName();

        List<User> users = userService.findByName(expectedName);

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getName()).isNotNull().isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list when user is not found")
    void findByName_ReturnsEmptyList_WhenUserIsNotFound() {
        BDDMockito.when(userRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<User> users = userService.findByName("user");

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findByEmail returns list of user when successful")
    void findByEmail_ReturnsListOfUser_WhenSuccessful() {
        String expectedEmail = UserCreator.createValidUser().getEmail();

        List<User> users = userService.findByEmail(expectedEmail);

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getEmail()).isNotNull().isEqualTo(expectedEmail);
    }

    @Test
    @DisplayName("findByEmail returns an empty list when user is not found")
    void findByEmail_ReturnsEmptyList_WhenUserIsNotFound() {
        BDDMockito.when(userRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<User> users = userService.findByEmail("user@gmail.com");

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findByAge returns list of user when successful")
    void findByAge_ReturnsListOfUser_WhenSuccessful() {
        Integer expectedAge = UserCreator.createValidUser().getAge();

        List<User> users = userService.findByAge(expectedAge);

        Assertions.assertThat(users)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(users.get(0).getAge()).isNotNull().isEqualTo(expectedAge);
    }

    @Test
    @DisplayName("findByAge returns an empty list when user is not found")
    void findByAge_ReturnsEmptyList_WhenUserIsNotFound() {
        BDDMockito.when(userRepositoryMock.findByAge(ArgumentMatchers.anyInt()))
                .thenReturn(Collections.emptyList());

        List<User> users = userService.findByAge(18);

        Assertions.assertThat(users)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns user when successful")
    void save_ReturnsUser_WhenSuccessful() {
        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBody();
        userPostRequestBody.setEmail("sandrohmtt@gmail.com"); // Os emails não podem ser iguais
        User userToBeSaved = UserCreator.createUserToBeSaved();

        BDDMockito.when(userMapperMock.toUser(userPostRequestBody))
                .thenReturn(userToBeSaved);

        User user = userService.save(userPostRequestBody);

        Assertions.assertThat(user)
                .isNotNull()
                .isEqualTo(userToBeSaved);
    }

    @Test
    @DisplayName("delete removes user when successful")
    void delete_RemovesUser_WhenSuccessFul() {
        Assertions.assertThatCode(() -> userService.delete(1L))
                .doesNotThrowAnyException();
    }

}