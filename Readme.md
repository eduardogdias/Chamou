# Chamou

Sistema de gerenciamento de Senhas e Guichês com exibição em um Painel centralizado.

---

## Regras de Negócio

- Ao criar uma nova Senha, ela será automaticamente marcada como Não atendida.
- Ao criar um novo Guichê, ele será marcado como Livre.
- Ao criar um novo Painel, ele será marcado como Atual, e todas as chamadas anteriores passarão para o status Antigo. 
- Além disso, a Senha associada será marcada como Atendida, e o Guichê será marcado como Ocupado.
- Não é possível editar ou excluir uma Senha. Apenas o status pode ser alterado.
- Caso a senha tenha sido criada de forma incorreta, ela deve ser descartada ou amassada (como na vida real).

---

## Endpoints (HTTP)

### Senhas

| Método | Endpoint       | Descrição                                                       |
| ------ | -------------- | --------------------------------------------------------------- |
| GET    | `/senhas`      | Lista todas as senhas cadastradas.                              |
| POST   | `/senhas`      | Cria uma nova senha (status padrão: não atendida)               |
| PUT    | `/senhas/{id}` | Atualiza o status de uma senha (atendida ou não)                |
| DELETE | `/senhas/{id}` | Exclui uma senha                                                |


### Guichês

| Método | Endpoint        | Descrição                                                      |
| ------ | --------------- | -------------------------------------------------------------- |
| GET    | `/guiches`      | Lista todos os guichês cadastrados                             |
| POST   | `/guiches`      | Cria um novo guichê (status padrão: livre)                     |
| PUT    | `/guiches/{id}` | Atualiza os dados de um guichê (ex: atendente, número, status) |
| DELETE | `/guiches/{id}` | Remove um guichê do sistema                                    |


### Painéis

| Método | Endpoint        | Descrição                                                       |
| ------ | --------------- | --------------------------------------------------------------- |
| GET    | `/paineis`      | Lista todas as senhas chamadas                                  |
| POST   | `/paineis`      | Chama uma nova senha (define como atual, altera senha e guichê) |
| PUT    | `/paineis/{id}` | Atualiza o status da chamada (ex: atual → antigo)               |
| DELETE | `/paineis/{id}` | Remove a chamada do sistema                                     |
