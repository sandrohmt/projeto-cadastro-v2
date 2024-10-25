package com.sandrohenrique.projeto_cadastro_v2.controller;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import com.sandrohenrique.projeto_cadastro_v2.requests.QuestionPostRequestBody;
import com.sandrohenrique.projeto_cadastro_v2.service.QuestionService;
import com.sandrohenrique.projeto_cadastro_v2.util.QuestionCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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
    @DisplayName("findById returns question when successful")
    void findById_ReturnsQuestion_WhenSuccessful() {
        Long expectedId = QuestionCreator.createValidQuestion().getId();

        Question question = questionController.findById(1L).getBody();

        Assertions.assertThat(question).isNotNull();

        Assertions.assertThat(expectedId).isEqualTo(question.getId());
    }

    @Test
    @DisplayName("save returns question when successful")
    void save_ReturnsQuestion_WhenSuccessful() {
//        Question question = questionController.save().getBody();
//
//        Assertions.assertThat(question).isNotNull();
//
//        Assertions.assertThat(expectedId).isEqualTo(question.getId());
    }
}