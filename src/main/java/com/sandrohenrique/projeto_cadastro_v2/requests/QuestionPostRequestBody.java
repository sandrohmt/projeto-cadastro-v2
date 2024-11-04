package com.sandrohenrique.projeto_cadastro_v2.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class QuestionPostRequestBody {
    @NotEmpty (message = "The question text cannot be empty")
    private String questionText;
}
