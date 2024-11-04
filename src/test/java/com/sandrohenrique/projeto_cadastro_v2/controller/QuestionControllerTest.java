package com.sandrohenrique.projeto_cadastro_v2.controller;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import com.sandrohenrique.projeto_cadastro_v2.exception.BadRequestException;
import com.sandrohenrique.projeto_cadastro_v2.requests.QuestionPostRequestBody;
import com.sandrohenrique.projeto_cadastro_v2.service.QuestionService;
import com.sandrohenrique.projeto_cadastro_v2.util.QuestionCreator;
import com.sandrohenrique.projeto_cadastro_v2.util.QuestionPostRequestBodyCreator;
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
class QuestionControllerTest {
    @InjectMocks
    QuestionController questionController;

    @Mock
    QuestionService questionServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(questionServiceMock.listAll())
                .thenReturn(List.of(QuestionCreator.createValidQuestion()));

        BDDMockito.when(questionServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(QuestionCreator.createValidQuestion());

        BDDMockito.when(questionServiceMock.save(ArgumentMatchers.any(QuestionPostRequestBody.class)))
                .thenReturn(QuestionCreator.createQuestionToBeSaved());

        BDDMockito.doNothing().when(questionServiceMock).delete(ArgumentMatchers.anyLong());
    }


    @Test
    @DisplayName("readQuestions returns list of question when successful")
    void readQuestions_ReturnsListOfQuestion_WhenSuccessful() {
        String expectedQuestionText = QuestionCreator.createValidQuestion().getQuestionText();

        List<Question> questions = questionController.readQuestions().getBody();

        Assertions.assertThat(questions)
                .isNotNull()
                .hasSize(1);

        Assertions.assertThat(expectedQuestionText).isEqualTo(questions.get(0).getQuestionText());
    }

    @Test
    @DisplayName("readQuestions returns an empty list when no questions are found")
    void readQuestions_ReturnsEmptyList_WhenNoQuestionsAreFound() {
        BDDMockito.when(questionServiceMock.listAll())
                .thenReturn(Collections.emptyList());

        List<Question> questions = questionController.readQuestions().getBody();

        Assertions.assertThat(questions)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findById returns question when successful")
    void findById_ReturnsQuestion_WhenSuccessful() {
        Long expectedId = QuestionCreator.createValidQuestion().getId();

        Question question = questionController.findById(1L).getBody();

        Assertions.assertThat(question).isNotNull();

        Assertions.assertThat(expectedId).isEqualTo(question.getId());
    }

    @Test
    @DisplayName("findById returns BadRequestException when question is not found")
    void findById_ReturnsThrowBadRequestException_WhenQuestionIsNotFound() {
        BDDMockito.when(questionServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenThrow(BadRequestException.class);

        Assertions.assertThatThrownBy(() -> questionController.findById(1L))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    @DisplayName("save returns question when successful")
    void save_ReturnsQuestion_WhenSuccessful() {
        Question question = questionController.saveQuestion(QuestionPostRequestBodyCreator.createQuestionPostRequestBody()).getBody();

        Assertions.assertThat(question)
                .isNotNull()
                .isEqualTo(QuestionCreator.createQuestionToBeSaved());
    }

    @Test
    @DisplayName("save throws ConstraintViolationException when question has invalid data")
    void save_ThrowsConstraintViolationException_WhenQuestionHasInvalidData() {
        BDDMockito.when(questionServiceMock.save(ArgumentMatchers.any(QuestionPostRequestBody.class)))
                .thenThrow(ConstraintViolationException.class);

        QuestionPostRequestBody questionPostRequestBody = QuestionPostRequestBodyCreator.createQuestionPostRequestBody();

        Assertions.assertThatThrownBy(() -> questionController.saveQuestion(questionPostRequestBody))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("delete removes question when successful")
    void delete_RemovesQuestion_WhenSuccessful() {
        Assertions.assertThatCode(() -> questionController.deleteQuestion(1L))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = questionController.deleteQuestion(1L);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete throw BadRequestException when attempting to remove first four questions ")
    void delete_throwBadRequestException_WhenAttemptingToRemoveFirstFourQuestions() {
        for (long id = 1L; id < 4; id++) {
            Long questionId = id;
            BDDMockito.doThrow(BadRequestException.class)
                    .when(questionServiceMock).delete(questionId);
        }

        Assertions.assertThatThrownBy(() -> questionController.deleteQuestion(1L))
                .isInstanceOf(BadRequestException.class);
    }
}