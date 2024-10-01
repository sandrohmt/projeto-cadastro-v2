package com.sandrohenrique.projeto_cadastro_v2.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class QuestionPostRequestBody {
    @NotEmpty (message = "The question text cannot be empty")
    private String questionText;
}
