
# Desafio PicPay

## Descrição

Este repositório contém a solução para o desafio de backend proposto pelo PicPay. 

- **Framework Utilizado:** Spring Boot
- **Linguagem:** Java
- **Dependências:**
  - Spring Boot
  - Lombok
  - Apache HttpComponents
  - JPA (Java Persistence API)
  - H2 (Banco de Dados em Memória)

## Estrutura do Projeto

O projeto está modularizado da seguinte forma:

- **`domain`**: Contém as classes principais do domínio, incluindo `User`, `UserType` e `Transaction`.
- **`dtos`**: Contém os Data Transfer Objects (DTOs), utilizando `records` para uma representação simples dos dados.
- **`infra`**: Contém as configurações da aplicação, incluindo a configuração do `RestTemplate` e tratamento de exceções.
- **`services`**: Contém a lógica de negócios e serviços principais, como `TransactionService` e `NotificationService`.
- **`repositories`**: Contém os repositórios JPA para acesso ao banco de dados.
- **`controllers`**: Contém os controladores REST para expor a API da aplicação.

## Configuração

### Dependências Maven

Adicione as seguintes dependências ao seu `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version>
    </dependency>
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpcore</artifactId>
        <version>4.4.15</version>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Configuração do `RestTemplate`

A configuração do `RestTemplate` está definida em `AppConfig.java`, que utiliza o Apache HttpComponents para configurar o cliente HTTP com suporte a SSL.

### Banco de Dados

O projeto utiliza o H2 como banco de dados em memória. A configuração é feita automaticamente pelo Spring Boot e pode ser acessada na URL `jdbc:h2:mem:testdb`.

## Endpoints

### POST /transfer

Realiza uma transferência entre dois usuários.

**Request:**

```json
{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
```

**Response:**

- **200 OK**: Retorna os detalhes da transação.
- **500 Internal Server Error**: Se ocorrer um erro durante a execução da transação.

## Requisitos e Funcionalidades

- **Cadastro de Usuários:** Nome Completo, CPF, e-mail e Senha. CPF e e-mails devem ser únicos.
- **Transferências:** Usuários podem enviar dinheiro entre si e para lojistas. Lojistas só recebem transferências.
- **Validação de Saldo:** Verifica se o usuário possui saldo suficiente antes de realizar uma transferência.
- **Autorização Externa:** Consulta um serviço externo para autorização da transferência.
- **Notificações:** Envia notificações por e-mail para os usuários envolvidos na transação.



