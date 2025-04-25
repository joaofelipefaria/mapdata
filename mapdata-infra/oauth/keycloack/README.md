Serviço keycloak:

Usa a imagem oficial do Keycloak.
Configura o usuário e senha do administrador (KEYCLOAK_ADMIN e KEYCLOAK_ADMIN_PASSWORD).
Configura o banco de dados PostgreSQL como backend (KC_DB, KC_DB_URL_HOST, etc.).
Expõe o Keycloak na porta 8080.
Serviço keycloak-db:

Usa a imagem oficial do PostgreSQL.
Configura o banco de dados, usuário e senha para o Keycloak.
Armazena os dados do banco em um volume chamado keycloak-db-data.
Volumes:

O volume keycloak-db-data é usado para persistir os dados do banco de dados.
Como Usar:
Salve o conteúdo acima em um arquivo chamado docker-compose.yml.
Execute o comando para iniciar os serviços:
Acesse o Keycloak no navegador em http://localhost:8080.
Faça login com o usuário admin e senha admin (ou os valores configurados no arquivo).

Para integrar o Keycloak à sua aplicação MapData, você pode usar o Keycloak como um provedor de autenticação e autorização. Aqui está um guia passo a passo para configurar a integração:

1. Configurar o Keycloak
Acesse o Keycloak:

Abra o navegador e acesse http://localhost:6080 (ou a porta configurada no docker-compose.yml).
Faça login com o usuário admin e senha admin (ou as credenciais configuradas).
Crie um Realm:

No painel do Keycloak, clique em "Add Realm".
Dê um nome ao seu Realm, por exemplo, mapdata-realm.
Crie um Cliente (Client):

No menu do Realm, vá para Clients > Create.
Configure o cliente:
Client ID: mapdata-client
Client Protocol: openid-connect
Access Type: confidential (para maior segurança).
Salve as configurações.
Obtenha as Credenciais do Cliente:

Após criar o cliente, vá para a aba Credentials.
Copie o valor de Client Secret. Você precisará disso na aplicação.
Configure os Redirecionamentos:

Na aba Settings do cliente, configure:
Valid Redirect URIs: URLs da sua aplicação, como http://localhost:3000/* ou http://localhost:8080/*.
Crie Usuários:

Vá para Users > Add User.
Adicione usuários e configure senhas na aba Credentials.