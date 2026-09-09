ApiTeste — API de Gestão de Alunos e Notas

⚠️ Projeto em andamento (estudo de Spring Boot + JPA). Estrutura e endpoints ainda em desenvolvimento.

Sobre

API REST para cadastro de alunos e lançamento de notas, desenvolvida com fins de estudo de Spring Boot, JPA/Hibernate e arquitetura em camadas (MVC).

Stack
Java com Spring Boot
Jakarta Persistence (JPA) / Hibernate
H2 Database (banco em memória)
Padrão de arquitetura MVC (Model - Repository - Service - Controller)
Estrutura do projeto
com.example.apiteste
├── model         → entidades JPA (AlunoEntity, NotaAlunoEntity)
├── status         → enums (AlunoStatus)
├── repository     → interfaces JpaRepository
├── service        → regras de negócio
└── controller     → endpoints REST
Modelagem
AlunoEntity
Campo	Tipo	Descrição
id	Long	Chave primária (auto gerada)
nome	String	Nome do aluno
notaAlunoEntity	List<NotaAlunoEntity>	Notas do aluno (@OneToMany, mapeado pelo lado NotaAlunoEntity)
alunoStatus	AlunoStatus	Situação do aluno (enum)
NotaAlunoEntity
Campo	Tipo	Descrição
id	Long	Chave primária (auto gerada)
notas	Double	Valor da nota
alunoEntity	AlunoEntity	Aluno dono da nota (@ManyToOne, dono do relacionamento)
AlunoStatus (enum)
PENDENTE — status inicial do aluno ao ser cadastrado
APROVADO
RECUPERACAO
REPROVADO

Relacionamento: um aluno possui várias notas (1:N). A tabela nota_aluno_entity guarda a chave estrangeira aluno_entity_id.

Endpoints
Cadastrar aluno

Cadastra um novo aluno, iniciando com status PENDENTE.

POST /alunos
Salvar nota de um aluno

Cadastra uma nova nota vinculada a um aluno existente.

POST /salvarNota/{id}
{id} — id do aluno que receberá a nota
Corpo da requisição (JSON):
json
{
  "notas": 8.5
}
Se o aluno não existir, a nota não é salva.
Como rodar
Clone o repositório
Rode a aplicação Spring Boot (via IDE ou mvn spring-boot:run)
A API sobe em http://localhost:8080
Console do H2 disponível em http://localhost:8080/h2-console
Próximos passos
Implementar endpoints de busca (listar alunos, buscar por id, listar notas de um aluno)
Calcular média/status automático do aluno a partir das notas
Padronizar respostas HTTP com ResponseEntity
Revisar nomenclatura de rotas seguindo convenções REST
