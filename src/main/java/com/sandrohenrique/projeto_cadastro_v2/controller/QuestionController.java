package com.sandrohenrique.projeto_cadastro_v2.controller;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import com.sandrohenrique.projeto_cadastro_v2.requests.QuestionPostRequestBody;
import com.sandrohenrique.projeto_cadastro_v2.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity <List<Question>> readQuestions() {
        return new ResponseEntity<>(questionService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity <Question> findById(@PathVariable Long id) {
        return new ResponseEntity<>(questionService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> saveQuestion(@RequestBody @Valid QuestionPostRequestBody questionPostRequestBody) { // O @Valid é para o spring fazer a validação automaticamente
        return new ResponseEntity<>(questionService.save(questionPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Não coloquei os dois argumentos dentro do ResponseEntity pois o delete retorna void
    }
}

// Renomear os métodos daqui