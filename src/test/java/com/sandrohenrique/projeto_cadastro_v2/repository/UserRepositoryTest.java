package com.sandrohenrique.projeto_cadastro_v2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Vai indicar pro spring que essa é uma classe de teste que vai testar um repository JPA
@ActiveProfiles("test") // Isso serve para o spring usar o application-test e não o application normal
class UserRepositoryTest { // Testes em repositórios são necessários apenas nos que a gente fez a @Query manualmente, os outros na teoria o JPA já testou automaticamente
// Não é uma boa ideia usar o mesmo banco de dados da produção para os testes unitários. É necessário criar outro banco de dados apenas para esses testes.
// Uma boa solução é usar um banco de dados em memória, como o H2
    @Test
    void findByName() {

    }
}