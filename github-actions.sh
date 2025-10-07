# Carrega as variáveis
source ./variables.sh

echo "Autenticando no GitHub CLI para o repositório ${GITHUB_REPO_NAME}"
gh auth status || gh auth login --hostname github.com --web

echo "Configurando GitHub Secrets automaticamente"
gh secret set SPRING_DATASOURCE_URL --repo "$GITHUB_REPO_NAME" --body "jdbc:sqlserver://${SERVER_NAME}.database.windows.net:1433;database=${DBNAME};user=${USERNAME}@${SERVER_NAME};password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
gh secret set SPRING_DATASOURCE_USERNAME --repo "$GITHUB_REPO_NAME" --body "$USERNAME"
gh secret set SPRING_DATASOURCE_PASSWORD --repo "$GITHUB_REPO_NAME" --body "$PASSWORD"
echo "Secrets configurados com sucesso no repositório GitHub: $GITHUB_REPO_NAME"
