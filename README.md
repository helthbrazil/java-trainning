# Treinamento Java 21 com Spring Boot 3.x

Este projeto é uma estrutura base para treinamento de desenvolvimento backend utilizando as melhores práticas, padrões arquiteturais modernos e diretrizes de desenvolvimento estritas.

## 🚀 Tecnologias
- **Java 21**
- **Spring Boot 3.3.0**
- **Spring Data JPA**
- **H2 Database** (para execução local e testes rápidos)
- **PostgreSQL Driver** (para ambientes de produção/homologação)
- **Lombok**
- **Validation** (validação de payloads com Bean Validation)
- **JUnit 5 / Mockito / AssertJ** (testes unitários e de integração)

## 📁 Estrutura de Pastas e Padrão Arquitetural
A arquitetura segue o fluxo de camadas estrito:
```
controller -> service (interface & impl) -> repository
```

### Divisão de Pacotes:
- `controller`: Expõe os endpoints HTTP, valida os dados de entrada e direciona para as regras de negócio.
- `dto`: Records Java para input (`UsuarioRequestDTO`) e output (`UsuarioResponseDTO`), garantindo isolamento da entidade.
- `exception`: Manipulador global de exceções (`GlobalExceptionHandler`) e exceções customizadas.
- `model`: Entidade JPA (`Usuario`) com convenção de nomenclatura snake_case e PK padrão.
- `repository`: Interfaces de acesso a dados com Spring Data JPA.
- `service`: Interfaces de negócio e implementações (`UsuarioServiceImpl`) utilizando o padrão **Early Return (Bouncer Pattern)**.

## ⚙️ Configuração Local
Para rodar localmente, o perfil ativo é o `Local`, configurado em `src/main/resources/application.properties` e `src/main/resources/application-Local.properties`.

## 🛠️ Comandos Úteis
Como o ambiente Maven padrão pode utilizar versões anteriores do JDK no sistema, utilize a variável `JAVA_HOME` explicitamente para garantir o uso do Java 21:

```bash
# Compilar o projeto
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 mvn clean compile

# Executar testes unitários e de integração
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 mvn clean test

# Gerar o build final empacotado (repackaged jar)
JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64 mvn clean verify
```

## 🔌 Endpoints Disponíveis
- **POST** `/api/usuarios`: Cria um novo usuário.
- **GET** `/api/usuarios/{id}`: Busca um usuário por id.
- **GET** `/api/usuarios`: Lista todos os usuários cadastrados.
- **PUT** `/api/usuarios/{id}`: Atualiza os dados de um usuário existente.
- **DELETE** `/api/usuarios/{id}`: Exclui um usuário por id.