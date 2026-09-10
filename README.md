# ApiTeste — API de Gestão de Alunos e Notas

API REST para cadastro de alunos, lançamento de notas e cálculo automático de status acadêmico, desenvolvida com fins de estudo de Spring Boot, JPA/Hibernate, arquitetura em camadas (MVC) e testes automatizados.

## Stack

- **Java** com **Spring Boot**
- **Jakarta Persistence (JPA)** / Hibernate
- **H2 Database** (banco em memória)
- **JUnit 5** + **Mockito** (testes unitários)
- Padrão de arquitetura **MVC** (Model - Repository - Service - Controller)

## Estrutura do projeto

```
com.example.apiteste
├── model         → entidades JPA (AlunoEntity, NotaAlunoEntity)
├── status         → enums (AlunoStatus)
├── repository     → interfaces JpaRepository
├── service        → regras de negócio
└── controller     → endpoints REST
```

## Modelagem

### AlunoEntity
| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | Chave primária (auto gerada) |
| `nome` | `String` | Nome do aluno |
| `notaAlunoEntity` | `List<NotaAlunoEntity>` | Notas do aluno (`@OneToMany`, mapeado pelo lado `NotaAlunoEntity`) |
| `alunoStatus` | `AlunoStatus` | Situação do aluno (enum) |

### NotaAlunoEntity
| Campo | Tipo | Descrição |
|---|---|---|
| `id` | `Long` | Chave primária (auto gerada) |
| `notas` | `Double` | Valor da nota |
| `alunoEntity` | `AlunoEntity` | Aluno dono da nota (`@ManyToOne`, dono do relacionamento) |

### AlunoStatus (enum)
- `PENDENTE` — status inicial do aluno ao ser cadastrado
- `APROVADO` — média ≥ 7
- `RECUPERACAO` — média entre 6 e 7
- `REPROVADO` — média < 6

**Relacionamento:** um aluno possui várias notas (`1:N`). A tabela `nota_aluno_entity` guarda a chave estrangeira `aluno_entity_id`. A referência circular entre as entidades é resolvida via `@JsonManagedReference`/`@JsonBackReference` na serialização JSON.

## Endpoints

### Alunos

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/alunos` | Cadastra um novo aluno (status inicial `PENDENTE`) |
| `GET` | `/alunos` | Lista todos os alunos |
| `GET` | `/alunos/{id}` | Busca um aluno por id — `404` se não existir |

### Notas

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/alunos/{id}/notas` | Cadastra uma nova nota vinculada ao aluno — `404` se o aluno não existir |
| `GET` | `/alunos/{id}/notas` | Lista as notas de um aluno |
| `POST` | `/alunos/{id}/notas/media` | Calcula a média (exige 4 notas lançadas) e atualiza o status do aluno |

**Corpo de uma nota (JSON):**
```json
{
  "notas": 8.5
}
```

## Testes

O projeto conta com testes unitários (JUnit 5 + Mockito) para a camada de `Service`:

- `AlunoServiceTest` — cobre salvar aluno (com/sem nome válido), listar todos, buscar por id (existente/inexistente)
- `NotaAlunoServiceTest` — cobre salvar nota (aluno existente/inexistente), listar notas, cálculo de média e atualização de status (reprovado, recuperação, aprovado, incluindo o caso de borda de média exatamente 10)

Os testes usam `@Mock` para simular os repositories (sem depender de banco real) e `@InjectMocks` para instanciar os services com essas dependências fake.

```bash
mvn test
```

## Como rodar

1. Clone o repositório
2. Rode a aplicação Spring Boot (via IDE ou `mvn spring-boot:run`)
3. A API sobe em `http://localhost:8080`
4. Console do H2 disponível em `http://localhost:8080/h2-console`