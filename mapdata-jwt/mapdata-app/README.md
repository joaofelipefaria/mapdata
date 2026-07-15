# MapData App

## Overview
The **MapData App** is an Angular application that serves as a user interface for managing map data and its metadata. It consumes the `mapdata-api` RESTful API to perform CRUD operations on data and metadata.

## Features
- User-friendly interface for managing map data and metadata.
- CRUD operations for map data and metadata.
- Integration with the `mapdata-api`.
- Fully containerized with Docker support.

## Project Structure
- **`src/app/components/`**: Contains the main components of the application, such as:
  - **`data-list/`**: Lists and manages map data.
  - **`data-form/`**: Form for creating or editing map data.
  - **`metadata-list/`**: Lists and manages metadata associated with map data.
  - **`metadata-form/`**: Form for creating or editing metadata.
- **`src/app/services/`**: Contains services for API communication.
- **`src/app/model/`**: Defines data models (`Data` and `Metadata`).
- **`docker/`**: Contains Docker configuration files.
- **`angular.json`**: Angular CLI configuration.
- **`package.json`**: Node.js dependency management.

## Main Libraries and Frameworks
- **Angular**: Framework for building the user interface.
- **RxJS**: For handling asynchronous streams.
- **Angular Forms**: For form management.
- **Angular HttpClient**: For API communication.
- **Bootstrap** (optional): For styling.

## Consumed Endpoints
The application consumes the following endpoints from the `mapdata-api`:

### Map Data
- **GET `/api/mapdata/all`**: Retrieve all map data.
- **GET `/api/mapdata/{id}`**: Retrieve specific map data by ID.
- **POST `/api/mapdata`**: Create new map data.
- **PUT `/api/mapdata/{id}`**: Update existing map data.
- **DELETE `/api/mapdata/{id}`**: Remove specific map data.

### Metadata
- **GET `/api/mapdata/{id}/metadata`**: Retrieve all metadata associated with specific map data.
- **POST `/api/mapdata/{id}/metadata`**: Create new metadata associated with specific map data.
- **PUT `/api/mapdata/{id}/metadata/{metadataId}`**: Update existing metadata.
- **DELETE `/api/mapdata/{id}/metadata/{metadataId}`**: Remove specific metadata.

## Docker Setup
The project includes Docker support for easy deployment.

### Docker Files
- **`docker-compose.yml`**: Defines the services and configurations for running the application in a containerized environment.
- **`Dockerfile`**: Builds the Docker image for the application.

### Running with Docker
1. Build the Docker image:
   ```bash
   docker build -t mapdata-app .
   ```
2. Start the services using Docker Compose:
   ```bash
   docker-compose up -d
   ```
3. Access the application at `http://localhost:<port>` (default port is defined in the `docker-compose.yml` file).

### Stopping the Services
To stop the running containers:
```bash
docker-compose down
```

## Requirements
- Node.js 16 or higher.
- Angular CLI.
- Docker and Docker Compose.

## How to Build and Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd mapdata-app
   ```
2. Install the dependencies:
   ```bash
   npm install
   ```
3. Run the application in development mode:
   ```bash
   ng serve
   ```
4. Access the application at `http://localhost:4200`.

## KEYCLOAK - OAUTH
1. Instalar Dependências
No seu projeto Angular, instale as bibliotecas necessárias:
npm install keycloak-angular keycloak-js

2. Configurar o Keycloak
Crie um arquivo de configuração para o Keycloak, por exemplo, keycloak.config.ts:

3. Inicializar o Keycloak no Angular
No arquivo app.module.ts, configure o Keycloak:

4. Proteger Rotas no Angular
No arquivo app-routing.module.ts, proteja as rotas usando o Keycloak:

1 vulnerability
5. Exibir Informações do Usuário
No componente, você pode exibir informações do usuário autenticado:

6. Configurar o Keycloak no Keycloak Admin Console
Certifique-se de que o cliente no Keycloak está configurado corretamente:

Client ID: mapdata-client
Access Type: public (para evitar a necessidade de um client secret em apps SPA).
Valid Redirect URIs: Adicione http://localhost:4200/* (ou o URL da sua aplicação Angular).
7. Testar a Integração
Inicie o Keycloak e sua aplicação Angular:
Acesse http://localhost:4200.
Você será redirecionado para a página de login do Keycloak.
Após autenticar, você será redirecionado de volta para a aplicação Angular.
Recursos Adicionais
Documentação do Keycloak Angular: https://www.npmjs.com/package/keycloak-angular
Documentação Oficial do Keycloak: https://www.keycloak.org/documentation