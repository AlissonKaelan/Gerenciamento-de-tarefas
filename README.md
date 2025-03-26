
# Documentação do Projeto Gerenciamento de Tarefas

## Índice
- [Descrição do Projeto](#descrição-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Endpoints da API](#endpoints-da-api)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Descrição do Projeto
O projeto **Gerenciamento de Tarefas** é uma aplicação web desenvolvida em Java utilizando o framework Spring Boot. O objetivo é permitir que os usuários possam criar, editar, visualizar e excluir tarefas, além de gerenciar suas prioridades e status.

## Tecnologias Utilizadas
- ![Java](https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg) **Java 19**
- ![Spring](https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg) **Spring Boot 3.4.3**
- ![Thymeleaf](https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/thymeleaf/thymeleaf-original.svg) **Thymeleaf** para renderização de templates
- ![JPA](https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg) **Spring Data JPA** para acesso a dados
- ![PostgreSQL](https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original.svg) **PostgreSQL** como banco de dados
- ![Maven](https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/maven/maven-original.svg) **Maven** como gerenciador de dependências
- ![Bootstrap](https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/bootstrap/bootstrap-original.svg) **Bootstrap** para estilização da interface

## Estrutura do Projeto
A estrutura do projeto é organizada da seguinte forma:

```
MultipleFiles/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── alisson/
│   │   │           └── gerenciamento_de_tarefas/
│   │   │               ├── api/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               ├── database/
│   │   │               ├── exeptions/
│   │   │               ├── service/
│   │   │               └── Convert/
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── alisson/
│                   └── gerenciamento_de_tarefas/
│                       └── service/
│                           └── TesteTarefaService.java
├── pom.xml
└── README.md
```

## Configuração do Ambiente
Para configurar o ambiente de desenvolvimento, siga os passos abaixo:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/AlissonKaelan/Gerenciamento-de-tarefas.git
   cd Gerenciamento-de-tarefas
   ```

2. **Instale as dependências:**
   ```bash
   mvn install
   ```

3. **Configure o banco de dados:**
   - Crie um banco de dados PostgreSQL e configure as credenciais no arquivo `application.properties`.

## Como Executar o Projeto
Para executar o projeto, utilize o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API
A aplicação possui os seguintes endpoints:

- `GET /` - Página inicial que lista todas as tarefas.
- `GET /add-new-task` - Página para adicionar uma nova tarefa.
- `POST /add-or-update-task` - Endpoint para adicionar ou atualizar uma tarefa.
- `GET /edit-task/{id}` - Página para editar uma tarefa existente.
- `DELETE /delete-task/{id}` - Endpoint para excluir uma tarefa.
- `GET /task-by-status` - Filtra as tarefas por status.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir um *pull request* ou relatar problemas.

## Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
