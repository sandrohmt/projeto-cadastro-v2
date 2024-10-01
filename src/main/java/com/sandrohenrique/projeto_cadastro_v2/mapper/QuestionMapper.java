package com.sandrohenrique.projeto_cadastro_v2.mapper;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import com.sandrohenrique.projeto_cadastro_v2.requests.QuestionPostRequestBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") // Injeção de dependências
public interface QuestionMapper {
    public abstract Question toQuestion(QuestionPostRequestBody questionPostRequestBody);
}
