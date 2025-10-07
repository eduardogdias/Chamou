# Coloque um valor único no mundo
NOME="chamou"

# Informações básicas
RG="rg-${NOME}"
#LOCATION="brazilsouth"
LOCATION="eastus2"

# SQL Server e Banco
SERVER_NAME="sqlserver-${NOME}"
USERNAME="admsql"
PASSWORD="Fiap@2tdsvms"
DBNAME="db-${NOME}"

# Application Insights
APP_INSIGHTS_NAME="insights-${NOME}"

# App Service Plan & WebApp
PLAN_NAME="plan-${NOME}"
WEBAPP_NAME="webapp-${NOME}"
RUNTIME="JAVA:17-java17"

# GitHub (mude para sua conta e repositório)
GITHUB="eduardogdias"
REPO="chamou"
GITHUB_REPO_NAME="${GITHUB}/${REPO}"
BRANCH="main"
