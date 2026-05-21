# Histórico de alterações realizadas com apoio de IA

---
## Data: 2026-05-20

**Ferramenta utilizada:** Gemini 3.5 Flash
**Responsável:** hebert
**Branch:** feat/setup-java-training
**Tarefa/Issue:** TR01
**Ambiente de trabalho:** Local

### Objetivo
Construir uma estrutura Java 21 com Spring Boot 3.x para treinamento contendo arquitetura em camadas e práticas de Clean Code.

### Arquivos alterados
- README.md
- .gitignore
- pom.xml
- src/main/java/br/gov/mg/pmmg/treinamento/TreinamentoApplication.java
- src/main/java/br/gov/mg/pmmg/treinamento/controller/UsuarioController.java
- src/main/java/br/gov/mg/pmmg/treinamento/dto/UsuarioRequestDTO.java
- src/main/java/br/gov/mg/pmmg/treinamento/dto/UsuarioResponseDTO.java
- src/main/java/br/gov/mg/pmmg/treinamento/exception/EmailAlreadyExistsException.java
- src/main/java/br/gov/mg/pmmg/treinamento/exception/GlobalExceptionHandler.java
- src/main/java/br/gov/mg/pmmg/treinamento/exception/UsuarioNotFoundException.java
- src/main/java/br/gov/mg/pmmg/treinamento/model/Usuario.java
- src/main/java/br/gov/mg/pmmg/treinamento/repository/UsuarioRepository.java
- src/main/java/br/gov/mg/pmmg/treinamento/service/UsuarioService.java
- src/main/java/br/gov/mg/pmmg/treinamento/service/impl/UsuarioServiceImpl.java
- src/main/resources/application.properties
- src/main/resources/application-Local.properties
- src/test/java/br/gov/mg/pmmg/treinamento/TreinamentoApplicationTests.java
- src/test/java/br/gov/mg/pmmg/treinamento/service/impl/UsuarioServiceImplTest.java

### Alterações realizadas com apoio de IA
- Criação do arquivo de configuração pom.xml para Maven com Spring Boot 3.3.0 e Java 21.
- Implementação da classe principal da aplicação e estrutura de pacotes.
- Definição da entidade JPA Usuario utilizando convenções snake_case, ID autoincremental e índice seguindo a nomenclatura padrão.
- Criação do repositório UsuarioRepository estendendo JpaRepository.
- Criação dos records UsuarioRequestDTO e UsuarioResponseDTO para entrada e saída de dados com anotações de validação.
- Desenvolvimento das exceções personalizadas UsuarioNotFoundException e EmailAlreadyExistsException.
- Implementação da camada de serviço com interface e implementação contendo tratamento de erros e padrão Early Return.
- Implementação do controlador REST UsuarioController expondo endpoints CRUD limpos.
- Criação do manipulador global de exceções GlobalExceptionHandler.
- Criação do arquivo de propriedades application.properties e do perfil application-Local.properties com banco de dados em memória H2.
- Escrita de testes unitários para a camada de serviço utilizando JUnit 5, Mockito e AssertJ seguindo o padrão AAA.
- Atualização do README.md com instruções de execução e lista de endpoints.

### Dados utilizados na interação com IA
- Foram utilizados apenas dados fictícios.
- Não foram compartilhados dados reais de produção ou sensíveis.
