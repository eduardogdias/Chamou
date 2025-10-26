source ./variables.sh


az provider register --namespace Microsoft.Sql
az provider register --namespace Microsoft.Web


# banco
echo "Criando RG"
az group create --name "$RG" --location "$LOCATION"

echo "Criando Server"
az sql server create -l "$LOCATION" -g "$RG" -n "$SERVER_NAME" -u "$USERNAME" -p "$PASSWORD" --enable-public-network true

echo "Criando DB"
az sql db create -g "$RG" -s "$SERVER_NAME" -n "$DBNAME" --service-objective Basic --backup-storage-redundancy Local --zone-redundant false

echo "Criando Firewall Rule"
az sql server firewall-rule create -g "$RG" -s "$SERVER_NAME" -n AllowAll --start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255


# web app
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

