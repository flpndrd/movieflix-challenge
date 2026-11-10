# Desafio — Movieflix Casos de Uso

## 📄 Descrição

Este projeto implementa a API REST do Movieflix com Spring Boot: listagem de gêneros e filmes, detalhes do filme com as avaliações e inserção de avaliações por membros, com controle de acesso por token JWT.

## 🎯 Objetivo do desafio

Implementar os casos de uso "Listar filmes" e "Visualizar detalhes do filme" de forma que os testes do projeto base passem.

## 🛠 Tecnologias utilizadas

- Java 25
- Spring Boot 4.1.1
- Spring Data JPA
- Hibernate
- Bean Validation
- Spring Security (Resource Server com JWT)
- Banco de dados H2
- JUnit

## 📐 Modelo de Domínio

- **User**: `userId`, `name`, `email` (único), `password`, com um ou mais papéis e as suas avaliações
- **Role**: `roleId`, `authority`
- **Genre**: `genreId`, `name`, com os seus filmes
- **Movie**: `movieId`, `title`, `subTitle`, `movieYear`, `imageUrl`, `synopsis`, com o gênero e as avaliações
- **Review**: `reviewId`, `text`, com o filme e o usuário

No JSON, os ids continuam como `id`, o ano como `year` e a imagem como `imgUrl`, como no contrato do curso.

## 📦 Carga Inicial do Banco de Dados

A aplicação carrega automaticamente 3 gêneros, 10 filmes, 3 avaliações e 2 usuários:

| Usuário | Senha | Papel |
|---|---|---|
| `bob@gmail.com` | `123456` | VISITOR |
| `ana@gmail.com` | `123456` | MEMBER |

## 🔎 Funcionalidades

### Login

| Método | Endpoint | Acesso |
|---|---|---|
| POST | /oauth2/token | Cliente da aplicação (Basic) |

### Gêneros

| Método | Endpoint | Acesso |
|---|---|---|
| GET | /genres | VISITOR, MEMBER |

### Filmes

| Método | Endpoint | Acesso |
|---|---|---|
| GET | /movies | VISITOR, MEMBER |
| GET | /movies/{movieId} | VISITOR, MEMBER |
| GET | /movies/{movieId}/reviews | VISITOR, MEMBER |

### Avaliações

| Método | Endpoint | Acesso |
|---|---|---|
| POST | /reviews | MEMBER |

### Usuários

| Método | Endpoint | Acesso |
|---|---|---|
| GET | /users/me | VISITOR, MEMBER |

- A listagem de filmes é paginada (`content` e `page`), ordenada pelo título e filtra pelo gênero no parâmetro `genreId`; sem o parâmetro, ou com `genreId=0`, lista todos os filmes.
- A listagem de gêneros é ordenada pelo id.
- A avaliação é gravada para o usuário logado.

## ⚠️ Tratamento de Exceções

Os erros seguem o formato `ProblemDetail` (RFC 9457).

- `400 Bad Request` — filme inexistente na inserção da avaliação, `grant_type` diferente de `password` ou parâmetro obrigatório ausente
- `401 Unauthorized` — token ausente ou inválido, ou credenciais de login inválidas
- `403 Forbidden` — usuário logado sem o papel exigido pela rota
- `404 Not Found` — filme inexistente
- `422 Unprocessable Content` — erro de validação, com uma entrada por erro na propriedade `errors` (`fieldName` e `message`)
  - avaliação — `text`: obrigatório e não pode ser vazio; `movieId`: obrigatório

## 🔐 Segurança

- **Login:** o `POST /oauth2/token` valida o cliente da aplicação (Basic) e o usuário (`username` e `password` no formulário), e devolve um JWT com as claims `username` e `authorities`.

## ⚙️ Variáveis de ambiente

| Variável | Padrão | Uso |
|---|---|---|
| `CLIENT_ID` | `myclientid` | Id do cliente da aplicação no login |
| `CLIENT_SECRET` | `myclientsecret` | Segredo do cliente da aplicação no login |
| `JWT_DURATION` | `86400` | Duração do token, em segundos |
| `CORS_ORIGINS` | `http://localhost:3000,http://localhost:5173` | Origens liberadas para o front-end |

Os valores padrão de `CLIENT_ID` e `CLIENT_SECRET` são os do ambiente do Postman do curso.

## 🚀 Como Executar

1. Clonar o repositório
2. Importar o projeto em sua IDE como projeto Maven
3. Executar a classe principal da aplicação (anotada com `@SpringBootApplication`) pela IDE ou via terminal:

```bash
./mvnw spring-boot:run
```

## ✅ Validação

Após executar a aplicação, o banco de dados pode ser acessado via H2 Console para verificação das tabelas e dados inseridos.

- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: sa
- Senha: (vazia)

## 🧪 Testes

Os testes de integração são os do projeto base do desafio e cobrem o controle de acesso (401 e 403), a listagem de gêneros, a listagem e os detalhes de filmes, a inserção de avaliações e a validação do texto (422); os testes de entidade conferem a estrutura do modelo de domínio:

```bash
./mvnw clean verify
```

## 📌 Exemplos de Requisições

### Login

```http
POST /oauth2/token
Authorization: Basic bXljbGllbnRpZDpteWNsaWVudHNlY3JldA==
Content-Type: application/x-www-form-urlencoded
```

```
grant_type=password&username=ana@gmail.com&password=123456
```

Nas requisições seguintes, o token vai no cabeçalho `Authorization: Bearer <access_token>`.

### Listagem de gêneros

```http
GET /genres
```

### Listagem paginada de filmes por gênero

```http
GET /movies?genreId=1&page=0&size=6
```

### Detalhes do filme

```http
GET /movies/1
```

### Avaliações do filme

```http
GET /movies/1/reviews
```

### Inserção de avaliação (MEMBER)

```http
POST /reviews
```

```json
{
  "text": "Gostei do filme!",
  "movieId": 1
}
```

### Dados do usuário logado

```http
GET /users/me
```
