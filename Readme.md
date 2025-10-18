# Chamou
 
Sistema de gerenciamento de Senhas e Guichês com exibição em um Painel centralizado.
 
---
 
## Regras de Negócio
 
- Ao criar uma nova Senha, ela será automaticamente marcada como Não atendida.
- Ao criar um novo Guichê, ele será marcado como Livre.
- Ao chamar uma senha, a chamada será marcada como Atual, e todas as chamadas anteriores passarão para o status Antigo.
- Além disso, a Senha associada será marcada como Atendida, e o Guichê será marcado como Ocupado.
- Não é possível editar ou excluir uma Senha. Apenas o status pode ser alterado.
- Caso a senha tenha sido criada de forma incorreta, ela deve ser descartada ou amassada (como na vida real).
- Senhas não podem ser chamadas se todos os guichês estiverem ocupados.
- Senhas preferêciais terão prioridade no painel.
- O painel mostra a senha atual em destaque e as três últimas senhas chamadas
- **A aplicação decide a próxima senha não atendida e o próximo guichê livre com base nos critérios acima.**
 
---
 
## Telas da Aplicação
 
![](images/retirarSenha.png)
![](images/guiches.png)
![](images/editarGuiche.png)
![](images/painel.png)
 
---
 
## Intruções
 
### 1. Faça o Fork do projeto
 
Acesse o repositório original e clique em **Fork**:
 
```bash
https://github.com/eduardogdias/chamou
```
 
 
 
### 2. Clone seu repositório no Azure CLI
 
```bash
git clone https://github.com/{seu-usuario}/{seu-repositorio}.git
cd {seu-repositorio}
```
 
 
 
### 3. Configure as variáveis do projeto (Nome dos recursos e GitHub)
 
Abra o arquivo `variables.sh`:
```bash
nano variables.sh
```
 
Se quiser personalizar nomes, região ou credenciais, altere neste arquivo.
**Obs:** Atualize também o valor de `GITHUB` e `REPO` com o seu GitHub e repositório.
 
 
 
### 4. Dê permissão e execute o script de criação e deploy
```bash
chmod +x deploy-chamou.sh
./deploy-chamou.sh
```
 
Ao final, será solicitada autorização do GitHub para que o Azure crie o workflow de deploy.
 
 
### 5. Configure os Secrets no GitHub
 
Foi criado um script que cria e define os Secrets automaticamente no GitHub (passo 5.1). Caso prefira, você pode criar os Secrets manualmente (passo 5.2)
 
#### 5.1 Secrets automáticos
 
Dê permissão e execute o script que define os Secrets:
```bash
chmod +x github-actions.sh
./github-actions.sh
```
Será necessário logar no GitHub novamente:
- Selecione `SSH`
- Selecione `No`
- Abra o link e cole o código gerado no terminal:
![](images/githubSecrets.png)
 
 
#### 5.2 Secrets manuais
Entre em: Settings → Secrets and variables → Actions → New repository secret
 
Crie os seguintes secrets:
```
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_DATASOURCE_URL=
```
Se não alterou nada no `variables.sh`, utilize:
 
 
- SPRING_DATASOURCE_USERNAME=`admsql`
 
 
- SPRING_DATASOURCE_PASSWORD=`Fiap@2tdsvms`
- SPRING_DATASOURCE_URL=
`jdbc:sqlserver://sqlserver-chamou.database.windows.net:1433;database=db-chamou;user=admsql@sqlserver-chamou;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;`
 
Se alterou o banco ou servidor, copie a string correta no portal Azure em:
Banco de Dados → Cadeias de Conexão → JDBC
 
 
 
### 6. Ajuste o arquivo .yml do GitHub Actions
 
O arquivo estará em: `.github/workflows/main_webapp-chamou.yml`
 
Localize o comando `run: mvn clean install` e adicione (a identação deve estar alinhada):
````yml
env:
  SPRING_DATASOURCE_URL: ${{ secrets.SPRING_DATASOURCE_URL }}
  SPRING_DATASOURCE_USERNAME: ${{ secrets.SPRING_DATASOURCE_USERNAME }}
  SPRING_DATASOURCE_PASSWORD: ${{ secrets.SPRING_DATASOURCE_PASSWORD }}
````
 
Faça o commit e espere o build e deploy serem feitos automaticamente.
 
 
### 7. Acesse a aplicação
 
Se você não alterou os nomes padrão do `variables.sh`, o link será:
````bash
webapp-chamou.azurewebsites.net
````
 
Caso tenha alterado:
````bash
webapp-<nome-do-webapp>.azurewebsites.net
````
 
Para acessar a aplicação, coloque o sufixo: `/web/senhas`
 
---
 
## Endpoints (Web)
 
### Senhas
 
| Entidade | Método | Endpoint                   | Descrição                         |
| -------- | ------ | -------------------------- | --------------------------------- |
| Senha    | GET    | `/web/senhas`              | Tela de Retirar Senha             |
| Guichê   | GET    | `/web/guiches`             | Tela de gerenciamento de Guichês  |
| Guichê   | GET    | `/web/guiches/editar/{id}` | Tela de atualização de um Guichê  |
| Chamadas | GET    | `/web/chamadas`            | Tela com o Painel de chamadas     |
 
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
 
 
### Chamadas
 
| Método | Endpoint         | Descrição                                                       |
| ------ | ---------------- | --------------------------------------------------------------- |
| GET    | `/chamadas`      | Lista todas as senhas chamadas                                  |
| POST   | `/chamadas`      | Chama uma nova senha (define como atual, altera senha e guichê) |
| PUT    | `/chamadas/{id}` | Atualiza o status da chamada (ex: atual → antigo)               |
| DELETE | `/chamadas/{id}` | Remove a chamada do sistema                                     |
 
---
 
## Exemplos de Requisições (JSON)
 
### Exemplo de Request **Senha**:
POST:
````json
{
    "tipo": "COMUM"
}
````
 
PUT:
````json
{
  "atendida": true
}
````
 
---
 
### Exemplo de Request **Guichê**:
POST:
````json
{
    "numero": "06",
    "atendente": "Raquel Freitas"
}
````
 
PUT:
````json
{
    "numero": "16",
    "atendente": "Raquel Silva Freitas",
    "livre": false
}
````
 
---
 
 
### Exemplo de Request **Chamada**:
POST:
````json
{
    "senhaId": "1",
    "guicheId": "2"
}
````
 
POST sem body:
````json
{
   
}
````
 
PUT:
````json
{
    "atual": false
}
````

---

Desenvolvido por **Eduardo Guilherme Dias**

[ [LinkedIn](https://www.linkedin.com/in/eduardo-gdias/) |  [GitHub](https://github.com/eduardogdias) ]

 
