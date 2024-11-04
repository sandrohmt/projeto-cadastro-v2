# DESAFIO - SISTEMA DE CADASTROS V2!
## Escopo do Projeto:
Neste desafio, são aplicados conhecimentos de:

- Orientação a Objetos
- Manipulação de Banco de Dados com Java
- Streams e lambdas
- Tratamento de Exceções
- Boas práticas de código
- A Ideia do Projeto:
- O projeto é um sistema de CADASTRO com integração a um banco de dados. O usuário pode cadastrar pessoas interessadas, alterar dados, excluir pessoas, listar todos os cadastros, entre outras funcionalidades.


## Passo a Passo:
### Passo 1 - Configuração do Banco de Dados e Estrutura Inicial
Banco de Dados: Configure um banco de dados relacional para armazenar as informações dos usuários e perguntas do formulário.
Entidades e Tabelas: Estruture o banco de dados com as tabelas necessárias para as entidades (e.g., Usuarios, Perguntas).

### Passo 2 - Cadastro de Usuário
O sistema apresenta as perguntas ao usuário, que deve responder com dados como nome, email, idade e altura.
Armazene as respostas no banco de dados, organizadas de acordo com as regras do projeto.

### Passo 3 - Menu Principal
O sistema exibe um menu com as seguintes opções:
- Cadastrar usuário
- Listar todos os usuários: Lista os nomes de todos os usuários cadastrados.
- Cadastrar nova pergunta: Adiciona uma nova pergunta ao formulário no banco de dados.
- Deletar pergunta do formulário: Permite apagar perguntas do formulário, exceto as 4 perguntas iniciais.
- Pesquisar usuário: Busca usuários por nome, idade ou email e exibe os resultados.

### Passo 4 - Adicionar e Remover Perguntas
- Adicionar Pergunta: O sistema atribui automaticamente o número da pergunta, garantindo consistência.
- Remover Pergunta: O usuário indica o número da pergunta para deletá-la. As primeiras 4 perguntas são fixas e não podem ser removidas.

### Passo 5 - Busca por Usuários
Implemente uma função para buscar usuários por nome, idade ou email. A busca deve permitir resultados parciais (e.g., buscar por “Luca” deve retornar “Lucas Almeida”, “Luca De Sá”, etc.).

### Passo 6 - Validações de Cadastro
#### Cadastro de Usuário:
- Nome com no mínimo 10 caracteres.
- Email com o símbolo “@”.
- Idade maior que 18 anos.
- Altura no formato decimal (e.g., "1,75").
- Emails duplicados não são permitidos.

#### Cadastro de Perguntas:
- O número da pergunta é atribuído automaticamente.
- O formato deve ser, por exemplo, “7 - Qual seu hobbie favorito?”

#### Deleção de Perguntas:
- O usuário pode indicar o número da pergunta para deletá-la diretamente.

##Adição de Testes Unitários para garantir a qualidade e a integridade da aplicação.
