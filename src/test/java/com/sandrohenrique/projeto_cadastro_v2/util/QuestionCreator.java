package com.sandrohenrique.projeto_cadastro_v2.util;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;

public class QuestionCreator {
    public static Question createQuestionToBeSaved() {
        return Question.builder()
                .questionText("Com o que você trabalha?")
                .build();
    }

    public static Question createValidQuestion() {
        return Question.builder()
                .id(1L)
                .questionText("Com o que você trabalha?")
                .build();
    }
}
