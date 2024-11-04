package com.sandrohenrique.projeto_cadastro_v2.util;

import com.sandrohenrique.projeto_cadastro_v2.requests.QuestionPostRequestBody;

public class QuestionPostRequestBodyCreator { // Usado para criar instâncias da entidade QuestionPostRequestBody, que é o objeto da requisição. Usado em testes de controladores e serviços que manipulam requisições
    public static QuestionPostRequestBody createQuestionPostRequestBody() {
        return QuestionPostRequestBody.builder()
                .questionText(QuestionCreator.createQuestionToBeSaved().getQuestionText())
                .build();
    }
}
