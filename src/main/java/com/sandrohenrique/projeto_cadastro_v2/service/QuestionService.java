package com.sandrohenrique.projeto_cadastro_v2.service;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import com.sandrohenrique.projeto_cadastro_v2.exception.BadRequestException;
import com.sandrohenrique.projeto_cadastro_v2.mapper.QuestionMapper;
import com.sandrohenrique.projeto_cadastro_v2.repository.QuestionRepository;
import com.sandrohenrique.projeto_cadastro_v2.requests.QuestionPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public Question save(QuestionPostRequestBody questionPostRequestBody) {
        Question question = questionMapper.toQuestion(questionPostRequestBody);
        return questionRepository.save(question);
    }

    public List<Question> listAll() {
        return questionRepository.findAll();
    }

    public Question findByIdOrThrowBadRequestException(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Question not found!"));
    }

    public void delete(Long id) {
        if (isFirstFourQuestions(id)) {
            throw new BadRequestException("The first four questions cannot be deleted!");
        }
        questionRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    private boolean isFirstFourQuestions(Long id) {
        List<Question> allQuestions = questionRepository.findAll();
        int numberOfFirstQuestions = 4;
            for (int i = 0; i < numberOfFirstQuestions; i++) {
                if (Objects.equals(id, allQuestions.get(i).getId())) {
                    return true;
                }
            }
        return false;
    }
}
