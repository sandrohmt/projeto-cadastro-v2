package com.sandrohenrique.projeto_cadastro_v2.repository;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class QuestionRepositoryTest {
    @Autowired QuestionRepository questionRepository;

    @Test
    @DisplayName("save throw ConstraintViolationException when questionText is empty")
    void save_ThrowConstraintViolationException_WhenQuestioiTextIsEmpty() {
        Question question = new Question();

        Assertions.assertThatThrownBy(() -> this.questionRepository.save(question))
                .isInstanceOf(ConstraintViolationException.class);
    }

}