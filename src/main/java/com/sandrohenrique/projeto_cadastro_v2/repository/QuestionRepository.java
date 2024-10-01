package com.sandrohenrique.projeto_cadastro_v2.repository;

import com.sandrohenrique.projeto_cadastro_v2.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> { // Question é o tipo de entidade que o rep vai gerenciar, o Long é o tipo do Id de Question
}
