# Carrega as variáveis
source ./variables.sh


# Regitrar os serviços que serão utilizados (caso não tenham na sua assinatura):
az provider register --namespace Microsoft.Sql
az provider register --namespace Microsoft.Web

az provider register --namespace Microsoft.Insights
az provider register --namespace Microsoft.OperationalInsights
az provider register --namespace Microsoft.ServiceLinker
az extension add --name application-insights


# Criar os recursos Database:
echo "Criando RG"
az group create --name "$RG" --location "$LOCATION"

echo "Criando Server"
az sql server create -l "$LOCATION" -g "$RG" -n "$SERVER_NAME" -u "$USERNAME" -p "$PASSWORD" --enable-public-network true

echo "Criando DB"
az sql db create -g "$RG" -s "$SERVER_NAME" -n "$DBNAME" --service-objective Basic --backup-storage-redundancy Local --zone-redundant false

echo "Criando Firewall Rule"
az sql server firewall-rule create -g "$RG" -s "$SERVER_NAME" -n AllowAll --start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255



# Criar os recursos WebApp:
echo "Criando Application Insights"
az monitor app-insights component create \
  --app "$APP_INSIGHTS_NAME" \
  --location "$LOCATION" \
  --resource-group "$RG" \
  --application-type web


echo "Criando Plano de Serviço"
az appservice plan create \
  --name "$PLAN_NAME" \
  --resource-group "$RG" \
  --location "$LOCATION" \
  --sku F1 \
  --is-linux


echo "Criando WebApp"
az webapp create \
  --name "$WEBAPP_NAME" \
  --resource-group "$RG" \
  --plan "$PLAN_NAME" \
  --runtime "$RUNTIME"


echo "Habilitando autenticação Básica (/home/site/wwwroot)"
az resource update \
  --resource-group "$RG" \
  --namespace Microsoft.Web \
  --resource-type basicPublishingCredentialsPolicies \
  --name scm \
  --parent sites/"$WEBAPP_NAME" \
  --set properties.allow=true



# Conectando Web App com o Application Insights
echo "Conectando Web App com o Application Insights"
echo "Recuperando a String de Conexão do Application Insights"
CONNECTION_STRING=$(az monitor app-insights component show \
  --app "$APP_INSIGHTS_NAME" \
  --resource-group "$RG" \
  --query connectionString \
  --output tsv)


echo "Configurando as variáveis de ambiente do App e do Application Insights"
az webapp config appsettings set \
  --name "$WEBAPP_NAME" \
  --resource-group "$RG" \
  --settings \
    APPLICATIONINSIGHTS_CONNECTION_STRING="$CONNECTION_STRING" \
    ApplicationInsightsAgent_EXTENSION_VERSION="~3" \
    XDT_MicrosoftApplicationInsights_Mode="Recommended" \
    XDT_MicrosoftApplicationInsights_PreemptSdk="1" \
    SPRING_DATASOURCE_USERNAME="$USERNAME" \
    SPRING_DATASOURCE_PASSWORD="$PASSWORD" \
    SPRING_DATASOURCE_URL="jdbc:sqlserver://${SERVER_NAME}.database.windows.net:1433;database=${DBNAME};user=${USERNAME}@${SERVER_NAME};password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"
    

echo "Reiniciando o Web App"
az webapp restart \
  --name "$WEBAPP_NAME" \
  --resource-group "$RG"


echo "Criando a conexão do Web App com o Application Insights"
az monitor app-insights component connect-webapp \
  --app "$APP_INSIGHTS_NAME" \
  --web-app "$WEBAPP_NAME" \
  --resource-group "$RG"


echo "Configurando GitHub Actions para Build e Deploy automático"
az webapp deployment github-actions add \
  --name "$WEBAPP_NAME" \
  --resource-group "$RG" \
  --repo "$GITHUB_REPO_NAME" \
  --branch "$BRANCH" \
  --login-with-github



